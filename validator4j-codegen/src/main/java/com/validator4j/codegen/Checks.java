package com.validator4j.codegen;

import java.util.Objects;

// TODO move to module validator4j-util
public final class Checks {

    // TODO replace all project checks this this impl
    public static void nonNull(final Object value, final String fieldName) {
        Objects.requireNonNull(fieldName, "fieldName must not to be null");
        Objects.requireNonNull(value, fieldName + " must not to be null");
    }
}
