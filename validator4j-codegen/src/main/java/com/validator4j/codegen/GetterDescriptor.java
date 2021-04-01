package com.validator4j.codegen;

import lombok.Getter;
import lombok.NonNull;

/**
 * Getter descriptor.
 */
@Getter
public class GetterDescriptor {

    public static final String GETTER_NAME_PREFIX = "get";

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

    public GetterDescriptor(@NonNull final String name,
                            @NonNull final TypeDescriptor returnType,
                            @NonNull final TypeDescriptor enclosingType)
    {
        validateName(name);

        this.name = name;
        this.returnType = returnType;
        this.enclosingType = enclosingType;
    }

    private void validateName(@NonNull final String name) {
        final var isGetterName = name.startsWith(GETTER_NAME_PREFIX);
        if (isGetterName) return;

        throw new CodeGenException(
            String.format("Getter name should starts with 'get' but current name is '%s'", name)
        );
    }

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
