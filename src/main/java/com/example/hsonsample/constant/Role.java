package com.example.hsonsample.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    ADMIN("관리자"),
    USER("일반 사용자");

    private final String name;
}
