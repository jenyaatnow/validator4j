package com.validator4j.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation is used to indicate classes for which required to generate a corresponding inheritor of
 * {@link ValidatableObject} for validation purpose.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Validatable {

    /**
     * {@link Validatable} annotation fully qualified name.
     */
    String NAME = "com.validator4j.core.Validatable";

    /**
     * The prefix of the generated validation classes.
     */
    String GENERATED_CLASS_PREFIX = "V";
}
