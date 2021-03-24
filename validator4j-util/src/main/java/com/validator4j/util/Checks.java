package com.validator4j.util;

import java.util.Objects;

public final class Checks {

    public static void nonNull(final Object value, final String fieldName) {
        Objects.requireNonNull(fieldName, "fieldName must not to be null");
        Objects.requireNonNull(value, fieldName + " must not to be null");
    }
}
