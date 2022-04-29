package com.example.hsonsample.service;

import com.example.hsonsample.domain.Member;
import com.example.hsonsample.dto.MemberJoinRequest;
import com.example.hsonsample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    // 회원가입
    public long join(MemberJoinRequest request) {
        Member member = new Member(request);
        memberRepository.save(member);

        return member.getId();
    }

    // 로그인

    // 로그아웃
}
