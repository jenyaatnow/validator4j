package com.validator4j.util;

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
}
