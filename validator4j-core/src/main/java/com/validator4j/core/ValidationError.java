package com.validator4j.core;

public interface ValidationError {
    String getPath();
    String getMessage();

    static ValidationError of(String path, String message) {
        return new ValidationErrorImpl(path, message);
    }
}
