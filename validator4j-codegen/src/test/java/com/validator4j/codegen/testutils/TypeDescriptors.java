package com.validator4j.codegen.testutils;

import com.validator4j.codegen.TypeDescriptor;
import com.validator4j.codegen.ValidatableType;
import com.validator4j.core.ValidatableInteger;
import lombok.NonNull;

public final class TypeDescriptors {
    public static TypeDescriptor INTEGER = new TypeDescriptor(Integer.class.getName(), ValidatableType.INTEGER);
    public static TypeDescriptor V_INTEGER =
        new TypeDescriptor(ValidatableInteger.class.getName(), ValidatableType.NON_V_TYPE);

    public static TypeDescriptor getUserType(@NonNull final Class<?> clazz) {
        return new TypeDescriptor(clazz.getName(), ValidatableType.USER_TYPE);
    }
}
