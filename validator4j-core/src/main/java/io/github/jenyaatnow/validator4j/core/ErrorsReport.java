package io.github.jenyaatnow.validator4j.core;

import java.util.Collection;

public interface ErrorsReport {

    /**
     * Returns all errors collected by the storage.
     *
     * @return errors collection.
     */
    Collection<ValidationError> getErrors();

    /**
     * Returns whether this report contains any errors or not.
     *
     * @return whether this report contains any errors or not.
     */
    boolean containsErrors();
}
