package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

public final class ValidatableShort extends ValidatableReference<Short> {

    public ValidatableShort(@NonNull final String path, final Short value, @NonNull final ValidationContext ctx) {
        super(path, value, ctx);
    }
}
