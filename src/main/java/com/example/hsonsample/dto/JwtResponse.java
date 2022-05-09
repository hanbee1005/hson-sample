package com.example.hsonsample.dto;

public class JwtResponse {
    private String accessToken;
    private String refreshToken;

    private JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public static JwtResponse of(String accessToken) {
        return new JwtResponse(accessToken);
    }
}
