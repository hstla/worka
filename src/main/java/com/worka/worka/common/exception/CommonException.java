package com.worka.worka.common.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
    private final CommonErrorCode errorCode;
    private final String message;

    public CommonException(CommonErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
