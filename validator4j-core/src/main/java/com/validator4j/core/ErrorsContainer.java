package com.validator4j.core;

import java.util.Collection;

/**
 * Base interface representing validation errors storage.
 */
public interface ErrorsContainer {

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
     * Returns all errors collected by the storage.
     *
     * @return errors collection.
     */
    Collection<ValidationError> getErrors();

    /**
     * Factory method to create validation errors storage.
     *
     * @return validation errors storage.
     */
    static ErrorsContainer getErrorsContainer() {
        return new ValidationErrors();
    }
}
