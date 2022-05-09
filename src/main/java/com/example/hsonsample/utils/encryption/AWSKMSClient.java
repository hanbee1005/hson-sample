package com.example.hsonsample.utils.encryption;

import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.CryptoResult;
import com.amazonaws.encryptionsdk.kms.KmsMasterKey;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class AWSKMSClient {
    private static final AwsCrypto crypto = AwsCrypto.builder().build();

    @Autowired
    private KmsMasterKeyProvider kmsMasterKeyProvider;

    byte[] encrypt(final String data) {
        final CryptoResult<byte[], KmsMasterKey> encryptResult = crypto.encryptData(kmsMasterKeyProvider, data.getBytes(StandardCharsets.UTF_8));
        return encryptResult.getResult();
    }

    String decrypt(final byte[] encryptedData) {
        final CryptoResult<byte[], KmsMasterKey> decryptResult = crypto.decryptData(kmsMasterKeyProvider, encryptedData);

        return new String(decryptResult.getResult(), StandardCharsets.UTF_8);
    }
}
