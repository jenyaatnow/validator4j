package com.validator4j.core;

import lombok.NonNull;

/**
 * This interface describes the validation error report.
 */
public interface ValidationError extends Comparable<ValidationError> {

    /**
     * Returns the property path to the invalid value from validation root.
     *
     * @return property path (e.g. bean.nested.id).
     */
    String getPath();

    /**
     * Returns validation error message.
     *
     * @return error message.
     */
    String getMessage();

    @Override
    default int compareTo(ValidationError o) {
        return getPath().compareTo(o.getPath());
    }

    /**
     * Factory method to get instance of {@link ValidationError}.
     *
     * @param path the property path to the invalid value from validation root.
     * @param message validation error message.
     * @return instance of {@link ValidationError}.
     */
    static ValidationError of(@NonNull final String path, @NonNull final String message) {
        return new ValidationErrorImpl(path, message);
    }
}
