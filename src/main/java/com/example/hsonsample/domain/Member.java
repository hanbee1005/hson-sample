package com.example.hsonsample.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String name;
    private String birthdate;
    private String phoneNumber;
}
