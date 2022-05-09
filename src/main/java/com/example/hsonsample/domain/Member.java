package com.example.hsonsample.domain;

import com.example.hsonsample.constant.Role;
import com.example.hsonsample.dto.MemberJoinRequest;
import com.example.hsonsample.utils.encryption.EncryptedStringConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    @Convert(converter = EncryptedStringConverter.class)
    private String password;

    private String name;
    private String birthdate;

    @Convert(converter = EncryptedStringConverter.class)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(MemberJoinRequest request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.name = request.getName();
        this.birthdate = request.getBirthdate();
        this.phoneNumber = request.getPhoneNumber();
        this.role = Role.USER;
    }

    public boolean isLoggedIn(String password) {
        // 비밀번호 비교 및 로그인 확인
        return true;
    }
}
