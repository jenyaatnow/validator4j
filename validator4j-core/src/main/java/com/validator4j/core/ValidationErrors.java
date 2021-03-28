package com.validator4j.core;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;

final class ValidationErrors implements ErrorsContainer {

    @Getter
    private final Collection<ValidationError> errors = new ArrayList<>();

    @Override
    public void add(@NonNull final ValidationError error) {
        errors.add(error);
    }
}
