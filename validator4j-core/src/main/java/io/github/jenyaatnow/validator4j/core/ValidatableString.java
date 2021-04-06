package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

public final class ValidatableString extends ValidatableReference<String> {

    public ValidatableString(@NonNull final String path, final String value, @NonNull final ValidationContext ctx) {
        super(path, value, ctx);
    }
}
