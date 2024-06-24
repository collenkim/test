package com.szs.test.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class SecretKeyUtil {

    private String secretKey;

    @PostConstruct
    public void init() {
        // 초기화 시점에 secretKey 생성
        this.secretKey = generateSecretKey();
    }

    public String getSecretKey() {
        return secretKey;
    }

    /**
     * SecretKey 생성
     *
     * @return
     */
    private String generateSecretKey() {
        byte[] randomBytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }

}
