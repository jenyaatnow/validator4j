package io.github.jenyaatnow.validator4j.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation is used to mark the particular field that should be ignored during code generation.
 * Thus validatable reflection for that field will not be generated.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface V4jIgnore {
}
