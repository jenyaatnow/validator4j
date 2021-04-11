package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.core.ValidatableCollection;
import io.github.jenyaatnow.validator4j.core.ValidatableObject;
import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import io.github.jenyaatnow.validator4j.core.ValidatableScalarCollection;
import io.github.jenyaatnow.validator4j.core.ValidatableValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public enum DataType {

    /**
     * Scalar value.
     */
    SCALAR(ValidatableValue.class, Set.of(Boolean.class, Number.class, String.class, Date.class, Enum.class)),

    /**
     * Collection of scalar values.
     */
    COLLECTION(ValidatableScalarCollection.class, Set.of(Collection.class)),

    /**
     * Collection of objects marked with {@link io.github.jenyaatnow.validator4j.core.Validatable}.
     */
    V_COLLECTION(ValidatableCollection.class, Set.of(Collection.class)),

    /**
     * Object marked with {@link io.github.jenyaatnow.validator4j.core.Validatable}.
     */
    VALIDATABLE(ValidatableObject.class, Set.of(Object.class)),

    /**
     * All {@link ValidatableReference} inheritors.
     */
    V4J(null, Set.of(ValidatableReference.class)),

    /**
     * Other data types.
     */
    OTHER(null, Set.of(Object.class))
    ;

    @Getter private final Class<? extends ValidatableReference> vClass;

    @Getter private final Set<Class<?>> jClasses;
}
