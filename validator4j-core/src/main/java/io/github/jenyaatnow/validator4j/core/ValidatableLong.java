package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

public final class ValidatableLong extends ValidatableReference<Long> {

    public ValidatableLong(@NonNull final String path, final Long value, @NonNull final ErrorsCollector errors) {
        super(path, value, errors);
    }
}
