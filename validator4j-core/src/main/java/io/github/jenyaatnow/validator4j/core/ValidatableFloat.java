package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

public final class ValidatableFloat extends ValidatableReference<Float> {

    public ValidatableFloat(@NonNull final String path, final Float value, @NonNull final ErrorsContainer errors) {
        super(path, value, errors);
    }
}
