package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

public final class ValidatableBoolean extends ValidatableReference<Boolean> {

    public ValidatableBoolean(@NonNull final String path, final Boolean value, @NonNull final ErrorsCollector errors) {
        super(path, value, errors);
    }
}
