package com.example.hsonsample.service;

import com.example.hsonsample.dto.MemberJoinRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthServiceTest {

    @Autowired AuthService authService;

    @Test
    @DisplayName("회원가입")
    void join() {
        // Given
        MemberJoinRequest member = MemberJoinRequest.builder()
                .email("test@ent-bc.com")
                .name("테스터")
                .password("123")
                .birthdate("20220509")
                .phoneNumber("01012345678")
                .build();

        // When
        long joinedMemberId = authService.join(member);

        // Then

    }
}