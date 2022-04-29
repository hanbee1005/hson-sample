package com.example.hsonsample.dto;

import javax.validation.constraints.NotEmpty;

public class MemberJoinRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

    private String birthdate;
    private String phoneNumber;
}
