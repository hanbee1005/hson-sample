package com.example.hsonsample.utils.encryption;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class EncryptionUtil {
    private final EncryptionService encryptionServiceBean;
    public static EncryptionService encryptionService;

    @PostConstruct
    private void initialize() {
        encryptionService = encryptionServiceBean;
        log.debug("Initialized encryptionService");
        log.debug("Test result for encryption - {}", encryptionService.encrypt("12345"));
    }
}
