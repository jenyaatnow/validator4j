package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

public final class ValidatableValue<TARGET> extends ValidatableReference<TARGET> {

    public ValidatableValue(@NonNull final String path, final TARGET value, @NonNull final ValidationContext ctx) {
        super(path, value, ctx);
    }
}
