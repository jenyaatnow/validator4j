package com.validator4j.codegen;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        this.name = name;
        this.vType = vType;
        this.typeParameters = typeParameters;
    }

    public Set<TypeDescriptor> getAllRelatedTypes() {
        final var types = new HashSet<>(getTypeParameters());
        types.add(this);
        return types;

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
