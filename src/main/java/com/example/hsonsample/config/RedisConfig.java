package com.example.hsonsample.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.resource.DnsResolvers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.transaction.SystemException;
import java.time.Duration;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.host}")
    private String endpoint;

    @Value("${spring.profiles}")
    private String profiles;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Netty 기반 ConnectionFactory
     *
     * @return
     * @throws SystemException
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() throws SystemException {
        LettuceConnectionFactory lettuceConnectionFactory;
        try {
            if (profiles.contains("default")) {
                lettuceConnectionFactory = new LettuceConnectionFactory(endpoint, redisPort);
            } else {
                RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
                clusterConfiguration.clusterNode(endpoint, redisPort);

                ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                        .enablePeriodicRefresh(Duration.ofSeconds(10))
                        .enableAllAdaptiveRefreshTriggers()
                        .build();
                ClientOptions clientOptions = ClusterClientOptions.builder()
                        .topologyRefreshOptions(topologyRefreshOptions)
                        .build();
                ClientResources clientResources = DefaultClientResources.builder().dnsResolver(DnsResolvers.JVM_DEFAULT).build();
                LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
                        .clientResources(clientResources)
                        .clientOptions(clientOptions).build();

                lettuceConnectionFactory = new LettuceConnectionFactory(clusterConfiguration, lettuceClientConfiguration);
            }

            // 패스워드가 있는경우
            // lettuceConnectionFactory.setPassword("");
            LOGGER.info("Redis Endpoint >>> " + endpoint);
        } catch (Exception exception) {
            throw new SystemException(exception.getMessage());
        }
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() throws SystemException {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(this.redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // redisTemplate.setValueSerializer(new
        // Jackson2JsonRedisSerializer<>(UserEntity.class)); //
        // <- 주고 받을 데이터(Entity)를 미리 설정 할때
        return redisTemplate;
    }
}

