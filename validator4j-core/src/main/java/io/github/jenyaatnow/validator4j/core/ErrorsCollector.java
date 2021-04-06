package io.github.jenyaatnow.validator4j.core;

import java.util.Collection;

interface ErrorsCollector {

    /**
     * Adds a validation error to the storage.
     *
     * @param error validation error.
     */
    void add(ValidationError error);

    /**
     * Adds all passed validation errors to the storage.
     *
     * @param errors validation errors.
     */
    void addAll(Collection<ValidationError> errors);

    /**
     * Factory method to create validation errors storage.
     *
     * @return validation errors storage.
     */
    static ErrorsCollector getErrorsCollector() {
        return new ValidationErrors();
    }
}
