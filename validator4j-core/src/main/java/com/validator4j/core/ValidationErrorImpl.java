package com.validator4j.core;

import com.validator4j.util.Checks;

final class ValidationErrorImpl implements ValidationError {

    private final String path;

    private final String message;

    ValidationErrorImpl(final String path, final String message) {
        Checks.nonNull(path, "path");
        Checks.nonNull(message, "message");

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
