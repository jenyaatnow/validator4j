package com.validator4j.core;

import java.util.Collection;

public interface ErrorsContainer {

    void add(ValidationError error);
    Collection<ValidationError> getErrors();

    static ErrorsContainer getErrorsContainer() {
        return new ValidationErrors();
    }
}
