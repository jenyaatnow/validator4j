package com.validator4j.core;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.NonNull;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Implementation of {@link ConstraintValidator} that delegates all validation job to Hibernate Validator.
 */
final class HibernateConstraintValidator implements ConstraintValidator {

    /**
     * Hibernate Validator instance.
     */
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public Collection<ValidationError> validate(@NonNull final Object target) {
        final var constraintViolations = VALIDATOR.validate(target)
            .stream()
            .map(violation -> ValidationError.of(violation.getPropertyPath().toString(), violation.getMessage()))
            .collect(Collectors.toSet());

        return constraintViolations;
    }
}
