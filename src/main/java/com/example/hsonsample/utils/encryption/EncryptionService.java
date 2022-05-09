package com.example.hsonsample.utils.encryption;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class EncryptionService {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    private final AWSKMSClient awskmsClient;

    public byte[] encrypt(@NonNull String data) {
        if (!StringUtils.isEmpty(data) &&
                !(activeProfile.contains("default") || activeProfile.contains("local"))) {
            return awskmsClient.encrypt(data);
        } else {
            return data.getBytes(StandardCharsets.UTF_8);
        }
    }

    public String decrypt(@NonNull byte[] data) {
        if (!StringUtils.isEmpty(data.toString()) &&
                !(activeProfile.contains("default") || activeProfile.contains("local"))) {
            return awskmsClient.decrypt(data);
        } else {
            return data.toString();
        }
    }
}
