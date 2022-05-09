package com.example.hsonsample.config;

import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AwsConfig {
    @Value("${aws.region}")
    private String region;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${aws.kms.key-arn}")
    private String keyArn;

    @Bean
    public KmsMasterKeyProvider kmsMasterKeyProvider() {
        /**
         * Data key caching
         * 단, 데이터 키 캐싱은 주의해서 사용해야 하는 AWS 암호화 SDK의 선택적 기능입니다.
         * 기본적으로 AWS 암호화 SDK는 모든 암호화 작업에 대해 새 데이터 키를 생성합니다.
         * 이 기술은 데이터 키의 과도한 재사용을 방지하는 암호화 모범 사례를 지원합니다.
         * 일반적으로 성능 목표를 달성하는 데 필요한 경우에만 데이터 키 캐싱을 사용하십시오.
         * 그런 다음 데이터 키 캐싱 보안 임계값 을 사용하여 비용 및 성능 목표를 달성하는 데 필요한 최소한의 캐싱을 사용하도록 합니다.
         * // Create a cache
         * CryptoMaterialsCache cache = new LocalCryptoMaterialsCache(cacheCapacity);
         *
         * // Create a caching CMM
         * CryptoMaterialsManager cachingCmm =
         *         CachingCryptoMaterialsManager.newBuilder().withMasterKeyProvider(keyProvider)
         *                 .withCache(cache)
         *                 .withMaxAge(maxEntryAge, TimeUnit.SECONDS)
         *                 .withMessageUseLimit(MAX_ENTRY_MSGS)
         *                 .build();
         *
         * // When the call to encryptData specifies a caching CMM,
         * // the encryption operation uses the data key cache
         * final AwsCrypto encryptionSdk = AwsCrypto.standard();
         * return encryptionSdk.encryptData(cachingCmm, myData, encryptionContext).getResult();
         */
        if (!(activeProfile.contains("default") || activeProfile.contains("local"))) {
            return KmsMasterKeyProvider.builder()
                    .withDefaultRegion(region)
                    .buildStrict(keyArn);
        } else {
            return KmsMasterKeyProvider.builder()
                    .withDefaultRegion(region)
                    .buildDiscovery();
        }
    }
}
