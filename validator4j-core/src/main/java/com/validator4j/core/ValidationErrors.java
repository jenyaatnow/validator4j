package com.validator4j.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

final class ValidationErrors implements ErrorsContainer {

    private final Collection<ValidationError> errors = new ArrayList<>();

    @Override
    public void add(final ValidationError error) {
        Objects.requireNonNull(error, "Error path must not be null");

        errors.add(error);
    }

    @Override
    public Collection<ValidationError> getErrors() {
        return errors;
    }
}
