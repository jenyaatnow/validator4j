package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

public final class ValidatableString extends ValidatableReference<String> {

    public ValidatableString(@NonNull final String path, final String value, @NonNull final ErrorsCollector errors) {
        super(path, value, errors);
    }
}
