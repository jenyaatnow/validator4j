package io.github.jenyaatnow.validator4j.codegen;

import lombok.Getter;
import lombok.NonNull;

/**
 * Getter descriptor.
 */
@Getter
public class GetterDescriptor {

    private static final String GETTER_NAME_PREFIX = "get";

    /**
     * Getter name.
     */
    @NonNull private final String fieldName;

    /**
     * Getter return type descriptor.
     */
    @NonNull private final TypeDescriptor returnType;

    /**
     * Descriptor of the class containing current getter.
     */
    @NonNull private final TypeDescriptor enclosingType;

    public GetterDescriptor(@NonNull final String fieldName,
                            @NonNull final TypeDescriptor returnType,
                            @NonNull final TypeDescriptor enclosingType)
    {
        this.fieldName = fieldName;
        this.returnType = returnType;
        this.enclosingType = enclosingType;
    }

    /**
     * Returns the name of the field whose contents are returned by the current getter.
     *
     * @return field name.
     */
    public String getName() {
        final var firstCharacter = fieldName.substring(0, 1).toUpperCase();
        final var rest = fieldName.substring(1);
        return GETTER_NAME_PREFIX + firstCharacter + rest;
    }
}
