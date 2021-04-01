package com.validator4j.codegen;

import com.validator4j.util.Checks;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * @return is this type generic.
     */
    boolean isGeneric() {
        return !typeParameters.isEmpty();
    }

    /**
     * @return simple class name.
     */
    String getSimpleName() {
        final var lastDotIdx = name.lastIndexOf('.');
        return name.substring(lastDotIdx + 1);
    }

    /**
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
