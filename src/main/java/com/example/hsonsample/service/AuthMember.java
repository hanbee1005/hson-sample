package com.example.hsonsample.service;

import com.example.hsonsample.domain.Member;

public class AuthMember {
    private Long id;
    private String email;
    private String name;

    private AuthMember(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
    }

    public static AuthMember of(Member member) {
        return new AuthMember(member);
    }
}
