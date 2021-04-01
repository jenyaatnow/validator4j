package com.validator4j.codegen;

import com.validator4j.util.Checks;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Type descriptor.
 */
@Getter
public class TypeDescriptor {

    /**
     * Fully qualified class name.
     */
    private final String name;

    /**
     * Corresponding {@link ValidatableType}.
     */
    private final ValidatableType vType;

    /**
     * Generic type parameter descriptors.
     */
    private final List<TypeDescriptor> typeParameters;

    public TypeDescriptor(@NonNull final String name, @NonNull final ValidatableType vType) {
        this.name = name;
        this.vType = vType;
        this.typeParameters = new ArrayList<>();
    }

    public TypeDescriptor(@NonNull final String name,
                          @NonNull final ValidatableType vType,
                          @NonNull final List<TypeDescriptor> typeParameters)
    {
        Checks.nonEmpty(typeParameters, "typeParameters");

        this.name = name;
        this.vType = vType;
        this.typeParameters = typeParameters;
    }

    /**
     * Determines whether the type represented by this class instance is generic.
     *
     * @return true if this type generic, otherwise - false.
     */
    boolean isGeneric() {
        return !typeParameters.isEmpty();
    }

    /**
     * Returns the simple name of the type represented by this class instance.
     *
     * @return simple class name.
     */
    String getSimpleName() {
        final var lastDotIdx = name.lastIndexOf('.');
        return name.substring(lastDotIdx + 1);
    }

    /**
     * Returns the package name of the type represented by this class instance.
     *
     * @return package name.
     */
    String getPackageName() {
        final var lastDotIdx = name.lastIndexOf('.');

        if (lastDotIdx > 0) {
            return name.substring(0, lastDotIdx);
        } else {
            throw new CodeGenException(String.format("Couldn't determine package name of '%s'", name));
        }
    }
}
