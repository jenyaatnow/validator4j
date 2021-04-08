package io.github.jenyaatnow.validator4j.codegen;

import lombok.Getter;
import lombok.NonNull;

/**
 * Getter descriptor.
 */
@Getter
public class FieldDescriptor {

    private static final String GETTER_NAME_PREFIX = "get";

    /**
     * Field name.
     */
    @NonNull private final String fieldName;

    /**
     * Getter field type descriptor.
     */
    @NonNull private final TypeDescriptor type;

    /**
     * Descriptor of the class containing current getter.
     */
    @NonNull private final TypeDescriptor enclosingType;

    public FieldDescriptor(@NonNull final String fieldName,
                           @NonNull final TypeDescriptor type,
                           @NonNull final TypeDescriptor enclosingType)
    {
        this.fieldName = fieldName;
        this.type = type;
        this.enclosingType = enclosingType;
    }

    /**
     * Returns the name of the field whose contents are returned by the current getter.
     *
     * @return field name.
     */
    public String getGetterName() {
        final var firstCharacter = fieldName.substring(0, 1).toUpperCase();
        final var rest = fieldName.substring(1);
        return GETTER_NAME_PREFIX + firstCharacter + rest;
    }
}
