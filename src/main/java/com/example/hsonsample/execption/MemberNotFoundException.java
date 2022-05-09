package com.example.hsonsample.execption;

import static com.example.hsonsample.constant.common.ExceptionConstant.MEMBER_NOT_FOUND;

public class MemberNotFoundException extends BusinessException {
    private static final String MESSAGE = "등록되지 않은 사용자입니다.";
    private static final String STATUS_CODE = MEMBER_NOT_FOUND;

    public MemberNotFoundException() {
        super(MESSAGE, STATUS_CODE);
    }

    public MemberNotFoundException(String message) {
        super(message, STATUS_CODE);
    }
}
