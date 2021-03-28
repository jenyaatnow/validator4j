package com.validator4j.core;

import lombok.NonNull;

public final class ValidatableDouble extends ValidatableReference<Double> {

    public ValidatableDouble(@NonNull final String path, final Double value, @NonNull final ErrorsContainer errors) {
        super(path, value, errors);
    }
}
