package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

public final class ValidatableByte extends ValidatableReference<Byte> {

    public ValidatableByte(@NonNull final String path, final Byte value, @NonNull final ValidationContext ctx) {
        super(path, value, ctx);
    }
}
