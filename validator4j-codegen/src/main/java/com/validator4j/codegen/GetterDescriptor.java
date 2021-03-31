package com.validator4j.codegen;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Getter descriptor.
 */
@Getter
@RequiredArgsConstructor
public class GetterDescriptor {

    /**
     * Getter name.
     */
    @NonNull private final String name;

    /**
     * Getter return type descriptor.
     */
    @NonNull private final TypeDescriptor returnType;

    /**
     * Descriptor of the class containing current getter.
     */
    @NonNull private final TypeDescriptor enclosingType;

    /**
     * @return name of the field whose contents are returned by the current getter.
     */
    public String getFieldName() {
        final var fieldName = name.substring(3);
        final var firstCharacter = fieldName.substring(0, 1).toLowerCase();
        final var rest = fieldName.substring(1);
        return firstCharacter + rest;
    }
}
