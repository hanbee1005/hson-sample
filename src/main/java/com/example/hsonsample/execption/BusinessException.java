package com.example.hsonsample.execption;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private String code;
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
