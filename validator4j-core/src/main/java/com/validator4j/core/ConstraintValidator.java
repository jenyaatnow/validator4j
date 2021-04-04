package com.validator4j.core;

import java.util.Collection;

/**
 * Validation by constraint annotations.
 */
interface ConstraintValidator {

    /**
     * Factory property to obtain {@link ConstraintValidator} instance.
     */
    ConstraintValidator instance = new HibernateConstraintValidator();

    /**
     * Performs validation by constraint annotations.
     *
     * @param target validation target.
     * @return collection of {@link ValidationError} representing constraint violations.
     */
    Collection<ValidationError> validate(Object target);
}
