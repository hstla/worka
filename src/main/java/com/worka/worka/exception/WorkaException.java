package com.worka.worka.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public abstract class WorkaException extends RuntimeException{
    public final Map<String, String> validation = new HashMap<>();

    public WorkaException(String message) {
        super(message);
    }

    public WorkaException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
