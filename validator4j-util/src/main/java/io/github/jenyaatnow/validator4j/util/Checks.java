package io.github.jenyaatnow.validator4j.util;

import lombok.NonNull;

import java.util.Collection;
import java.util.Objects;

public final class Checks {

    public static void nonNull(final Object value, final String fieldName) {
        Objects.requireNonNull(fieldName, "'fieldName' must not to be null.");
        Objects.requireNonNull(value, String.format("'%s' must not to be null.", fieldName));
    }

    public static void nonNullCustom(final Object value, final String message) {
        Objects.requireNonNull(message, "'message' must not to be null.");
        Objects.requireNonNull(value, message);
    }

    public static void nonEmpty(@NonNull final Collection<?> typeParameters, @NonNull final String fieldName) {
        if (typeParameters.isEmpty()) {
            throw new Validator4jException(String.format("'%s' must not to be empty.", fieldName));
        }
    }
}
