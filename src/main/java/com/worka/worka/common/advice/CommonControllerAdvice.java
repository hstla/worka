package com.worka.worka.common.advice;

import com.worka.worka.common.advice.response.ErrorResponse;
import com.worka.worka.common.advice.response.ErrorResponseWrapper;
import com.worka.worka.common.advice.response.FieldErrorResponse;
import com.worka.worka.common.exception.CommonErrorCode;
import com.worka.worka.common.exception.CommonException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("[Exception] cause: {}, message: {}" , ex, ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("예기치 못한 오류가 발생했습니다.");
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(CommonException ex) {
        log.error("[CommonException] cause: {}, message: {}" , ex, ex.getMessage());
        CommonErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .code(errorCode.getStatus())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseWrapper<List<FieldErrorResponse>>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("[ConstraintViolationException] cause: {}, message: {}" , ex, ex.getMessage());

        List<FieldErrorResponse> fieldErrorResponses = ex.getConstraintViolations().stream()
                .map(d -> FieldErrorResponse.builder()
                        .message(d.getMessage())
                        .fieldName(d.getPropertyPath().toString())
                        .build()
                )
                .toList();

        ErrorResponseWrapper<List<FieldErrorResponse>> resultResponse = ErrorResponseWrapper.<List<FieldErrorResponse>>builder()
                .data(fieldErrorResponses)
                .code(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(resultResponse);
    }
}
