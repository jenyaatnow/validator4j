package com.validator4j.core;

import java.util.Objects;

final class ValidationErrorImpl implements ValidationError {

    private final String path;

    private final String message;

    ValidationErrorImpl(final String path, final String message) {
        Objects.requireNonNull(path, "Error path must not be null");
        Objects.requireNonNull(path, "Error message must not be null");

        this.path = path;
        this.message = message;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
