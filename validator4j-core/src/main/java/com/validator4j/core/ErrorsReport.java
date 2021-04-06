package com.validator4j.core;

import java.util.Collection;

public interface ErrorsReport {

    /**
     * Returns all errors collected by the storage.
     *
     * @return errors collection.
     */
    Collection<ValidationError> getErrors();
}
