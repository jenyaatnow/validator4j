package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class TypeDescriptor {

    /**
     * Fully qualified class name.
     */
    private final String name;

    /**
     * Corresponding {@link DataType}.
     */
    private final DataType dataType;

    /**
     * Generic type parameter descriptors.
     */
    private final List<TypeDescriptor> typeParameters;

    public TypeDescriptor(@NonNull final String name, @NonNull final DataType dataType) {
        this.name = name;
        this.dataType = dataType;
        this.typeParameters = new ArrayList<>();
    }

    public TypeDescriptor(@NonNull final String name,
                          @NonNull final DataType dataType,
                          @NonNull final List<TypeDescriptor> typeParameters)
    {
        this.name = name;
        this.dataType = dataType;
        this.typeParameters = typeParameters;
    }

    /**
     * Recursively collects type descriptors of all types related to this getter.
     *
     * @return set of type descriptors.
     */
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

    /**
     * Returns the fully qualified name of v-type corresponding to type, represented by this class instance.
     * If particular instance represents a {@link DataType#OTHER} this method call throws an exception.
     * If particular instance represents a {@link DataType#VALIDATABLE} then the original class name will be
     * prepended with {@link Validatable#GENERATED_CLASS_PREFIX}. Other types returns the fully qualified name of the
     * {@link DataType#getVClass()}.
     *
     * @return v-type fully qualified name.
     */
    public String getVClassName() {
        if (dataType == DataType.VALIDATABLE) {
            return getPackageName() + '.' + Validatable.GENERATED_CLASS_PREFIX + getSimpleName();
        } else if (dataType == DataType.OTHER) {
            throw new CodeGenException(String.format("Type of '%s' is not v-type", name));
        } else {
            return dataType.getVClass().getName();
        }
    }
}
