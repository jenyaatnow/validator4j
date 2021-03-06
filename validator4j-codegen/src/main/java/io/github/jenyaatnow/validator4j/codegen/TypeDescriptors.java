package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.core.ValidatableCollection;
import io.github.jenyaatnow.validator4j.core.ValidatableObject;
import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import io.github.jenyaatnow.validator4j.core.ValidatableScalarCollection;
import io.github.jenyaatnow.validator4j.core.ValidatableValue;
import io.github.jenyaatnow.validator4j.core.ValidationContext;
import io.github.jenyaatnow.validator4j.util.Checks;
import lombok.NonNull;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public final class TypeDescriptors {

    public static final TypeDescriptor CHECKS = new TypeDescriptor(Checks.class, DataType.V4J);
    public static final TypeDescriptor VALIDATABLE_OBJECT = new TypeDescriptor(ValidatableObject.class, DataType.V4J);
    public static final TypeDescriptor VALIDATION_CONTEXT = new TypeDescriptor(ValidationContext.class, DataType.V4J);

    public static final TypeDescriptor VALIDATABLE_COLLECTION =
        new TypeDescriptor(ValidatableCollection.class, DataType.V4J);

    public static final TypeDescriptor VALIDATABLE_REFERENCE =
        new TypeDescriptor(ValidatableReference.class, DataType.V4J);


    public static final TypeDescriptor BOOLEAN = getValueType(Boolean.class);
    public static final TypeDescriptor V4J_BOOLEAN = getValidatableValueType(BOOLEAN);

    public static final TypeDescriptor INTEGER = getValueType(Integer.class);
    public static final TypeDescriptor V4J_INTEGER = getValidatableValueType(INTEGER);

    public static final TypeDescriptor DOUBLE = getValueType(Double.class);
    public static final TypeDescriptor V4J_DOUBLE = getValidatableValueType(DOUBLE);

    public static final TypeDescriptor STRING = getValueType(String.class);
    public static final TypeDescriptor V4J_STRING = getValidatableValueType(STRING);

    public static final TypeDescriptor DATE = getValueType(Date.class);
    public static final TypeDescriptor V4J_DATE = getValidatableValueType(DATE);

    public static final TypeDescriptor COLLECTION_OF_VALUES =
        new TypeDescriptor(Collection.class.getName(), DataType.COLLECTION, List.of(INTEGER));
    public static final TypeDescriptor V4J_COLLECTION_OF_VALUES =
        new TypeDescriptor(ValidatableScalarCollection.class.getName(), DataType.V4J, List.of(INTEGER));

    public static final TypeDescriptor VALIDATABLE_TYPE = new TypeDescriptor("com.sample.Test", DataType.VALIDATABLE);
    public static final TypeDescriptor V4J_VALIDATABLE_TYPE = new TypeDescriptor("com.sample.VTest", DataType.V4J);


    public static TypeDescriptor getUserType(@NonNull final Class<?> clazz) {
        return new TypeDescriptor(clazz.getName(), DataType.VALIDATABLE);
    }

    private static TypeDescriptor getValueType(@NonNull final Class<?> clazz) {
        return new TypeDescriptor(clazz.getName(), DataType.SCALAR);
    }

    private static TypeDescriptor getValidatableValueType(@NonNull final TypeDescriptor sourceType) {
        return new TypeDescriptor(ValidatableValue.class.getName(), DataType.V4J, List.of(sourceType));
    }
}
