package io.github.jenyaatnow.validator4j.codegen.testutils;

import io.github.jenyaatnow.validator4j.codegen.TypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.DataType;
import io.github.jenyaatnow.validator4j.core.ValidatableValue;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class TypeDescriptors {

    public static final TypeDescriptor INTEGER =
        new TypeDescriptor(Integer.class.getName(), DataType.VALUE);

    public static final TypeDescriptor V_INTEGER =
        new TypeDescriptor(ValidatableValue.class.getName(), DataType.OTHER);

    public static final TypeDescriptor INT_LIST =
        new TypeDescriptor(ArrayList.class.getName(), DataType.COLLECTION, List.of(INTEGER));


    public static TypeDescriptor getUserType(@NonNull final Class<?> clazz) {
        return new TypeDescriptor(clazz.getName(), DataType.VALIDATABLE);
    }
}
