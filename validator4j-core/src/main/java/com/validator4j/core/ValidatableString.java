package com.validator4j.core;

import lombok.NonNull;

public final class ValidatableString extends ValidatableReference<String> {

    public ValidatableString(@NonNull final String path, final String value, @NonNull final ErrorsContainer errors) {
        super(path, value, errors);
    }
}
