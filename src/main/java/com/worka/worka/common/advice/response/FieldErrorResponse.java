package com.worka.worka.common.advice.response;

import lombok.Builder;

@Builder
public record FieldErrorResponse(String fieldName, String message) {
}
