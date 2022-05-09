package com.example.hsonsample.service;

import com.example.hsonsample.domain.Member;
import com.example.hsonsample.execption.MemberNotFoundException;
import com.example.hsonsample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthManager {
    private final MemberRepository memberRepository;

    public Member loadUserByUsername(String email) throws MemberNotFoundException {
        return Optional.ofNullable(memberRepository.findByEmail(email))
                .orElseThrow(()-> new MemberNotFoundException("등록되지 않은 사용자 입니다"));
    }

    public Object generateAuthMember(Member member) {
        return AuthMember.of(member);
    }
}
