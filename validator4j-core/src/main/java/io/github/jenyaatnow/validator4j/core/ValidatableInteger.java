package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

public final class ValidatableInteger extends ValidatableReference<Integer> {

    public ValidatableInteger(@NonNull final String path, final Integer value, @NonNull final ValidationContext ctx) {
        super(path, value, ctx);
    }
}
