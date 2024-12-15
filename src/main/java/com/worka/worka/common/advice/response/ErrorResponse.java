package com.worka.worka.common.advice.response;

import lombok.Builder;

@Builder
public record ErrorResponse(int code, String message) {
}
