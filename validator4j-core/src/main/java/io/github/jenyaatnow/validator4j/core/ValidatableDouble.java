package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

public final class ValidatableDouble extends ValidatableReference<Double> {

    public ValidatableDouble(@NonNull final String path, final Double value, @NonNull final ErrorsCollector errors) {
        super(path, value, errors);
    }
}
