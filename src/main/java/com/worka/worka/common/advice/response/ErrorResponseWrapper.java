package com.worka.worka.common.advice.response;

import lombok.Builder;

@Builder
public record ErrorResponseWrapper<T>(int code, String message, T data) {
}
