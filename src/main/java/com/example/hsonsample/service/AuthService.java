package com.example.hsonsample.service;

import com.example.hsonsample.dto.MemberJoinRequest;
import com.example.hsonsample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    // 회원가입
    public void join(MemberJoinRequest request) {

    }

    // 로그인

    // 로그아웃
}
