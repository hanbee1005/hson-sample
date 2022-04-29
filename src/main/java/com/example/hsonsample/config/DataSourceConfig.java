package com.example.hsonsample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import java.nio.charset.StandardCharsets;

@Configuration
public class DataSourceConfig {
    private static final String MYSQL_FORMAT = "jdbc:mysql://%s/hsondb?useMysqlMetadata=true&createDatabaseIfNotExist=true&useUnicode=yes&characterEncoding=UTF-8&serverTimezone=UTC";

    @Value("${resources.root}${resources.path.gig-endpoint}")
    private Resource endpoint;

    @Value("${resources.root}${resources.path.gig-username}")
    private Resource username;

    @Value("${resources.root}${resources.path.gig-password}")
    private Resource password;

    @Value("${resources.driver-class}")
    private String driverClass;

    @Bean
    public DataSource getDataSource() throws SystemException {
        return DataSourceBuilder.create()
                .driverClassName(driverClass)
                .url(String.format(MYSQL_FORMAT, readValueFromResource(endpoint)))
                .username(readValueFromResource(username))
                .password(readValueFromResource(password))
                .build();
    }

    private String readValueFromResource(Resource resource) throws SystemException {
        try {
            return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8)
                    .trim();
        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }
    }
}
