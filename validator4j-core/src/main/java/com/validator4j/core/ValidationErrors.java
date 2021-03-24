package com.validator4j.core;

import com.validator4j.util.Checks;

import java.util.ArrayList;
import java.util.Collection;

final class ValidationErrors implements ErrorsContainer {

    private final Collection<ValidationError> errors = new ArrayList<>();

    @Override
    public void add(final ValidationError error) {
        Checks.nonNull(error, "error");

        errors.add(error);
    }

    @Override
    public Collection<ValidationError> getErrors() {
        return errors;
    }
}
