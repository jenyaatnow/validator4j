package com.validator4j.core;

public final class ValidatableInteger extends ValidatableReference<Integer> {

    public ValidatableInteger(final String path, final Integer value, final ErrorsContainer errors) {
        super(path, value, errors);
    }
}
