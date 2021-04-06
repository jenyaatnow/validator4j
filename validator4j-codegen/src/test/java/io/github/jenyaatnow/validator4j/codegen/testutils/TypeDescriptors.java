package io.github.jenyaatnow.validator4j.codegen.testutils;

import io.github.jenyaatnow.validator4j.codegen.TypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.ValidatableType;
import io.github.jenyaatnow.validator4j.core.ValidatableInteger;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class TypeDescriptors {

    public static TypeDescriptor INTEGER =
        new TypeDescriptor(Integer.class.getName(), ValidatableType.INTEGER);

    public static TypeDescriptor V_INTEGER =
        new TypeDescriptor(ValidatableInteger.class.getName(), ValidatableType.NON_V_TYPE);

    public static TypeDescriptor INT_LIST =
        new TypeDescriptor(ArrayList.class.getName(), ValidatableType.COLLECTION, List.of(INTEGER));


    public static TypeDescriptor getUserType(@NonNull final Class<?> clazz) {
        return new TypeDescriptor(clazz.getName(), ValidatableType.USER_TYPE);
    }
}
