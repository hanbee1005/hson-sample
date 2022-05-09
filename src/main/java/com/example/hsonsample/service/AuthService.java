package com.example.hsonsample.service;

import com.example.hsonsample.domain.Member;
import com.example.hsonsample.dto.JwtResponse;
import com.example.hsonsample.dto.MemberJoinRequest;
import com.example.hsonsample.dto.MemberLoginRequest;
import com.example.hsonsample.execption.BusinessException;
import com.example.hsonsample.repository.MemberRepository;
import com.example.hsonsample.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.hsonsample.constant.common.ExceptionConstant.LOGIN_FAIL;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    public long join(MemberJoinRequest request) {
        Member member = new Member(request);
        memberRepository.save(member);

        return member.getId();
    }

    // 로그인
    public JwtResponse login(MemberLoginRequest request) {
        Member member = Optional.ofNullable(memberRepository.findByEmail(request.getEmail()))
                .orElseThrow();

        if (member.isLoggedIn(request.getPassword())) {
            // token 생성 및 반환
            // TODO: Redis 저장 로직 추가
            return createToken(member);
        }

        throw new BusinessException("로그인에 실패하였습니다.", LOGIN_FAIL);
    }

    private JwtResponse createToken(Member member) {
        String token = jwtTokenProvider.generateToken(member);
        return JwtResponse.of(token);
    }

    // 로그아웃
}
