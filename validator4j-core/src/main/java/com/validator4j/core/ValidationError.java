package com.validator4j.core;

import lombok.NonNull;

public interface ValidationError {
    String getPath();
    String getMessage();

    static ValidationError of(@NonNull final String path, @NonNull final String message) {
        return new ValidationErrorImpl(path, message);
    }
}
