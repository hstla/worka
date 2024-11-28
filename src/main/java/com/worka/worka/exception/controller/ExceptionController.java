package com.worka.worka.exception.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.worka.worka.exception.WorkaException;
import com.worka.worka.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(WorkaException.class)
    public ResponseEntity<ErrorResponse> workaException( WorkaException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
            .code(String.valueOf(statusCode))
            .message(e.getMessage())
            .validation(e.getValidation())
            .build();

        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode)
            .body(body);

        return response;
    }
}
