package com.worka.worka.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode {
    INVALID_Id("유효하지 않은 ID 입니다.", HttpStatus.BAD_REQUEST.value());
    private final String message;
    private final int status;
}
