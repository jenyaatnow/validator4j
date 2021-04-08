package io.github.jenyaatnow.validator4j.codegen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public TypeDescriptor(@NonNull final Class<?> clazz, @NonNull final DataType dataType) {
        this(clazz.getName(), dataType);
    }

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
        final var types = getTypeParameters().stream()
            .flatMap(type -> type.getAllRelatedTypes().stream())
            .collect(Collectors.toCollection(HashSet::new));

        types.add(this);
        return types;
    }

    /**
     * Constructs class name with generic type parameters without package name.
     * Example: {@code HashMap<Integer, List<Optional<String>>>}.
     *
     * @return name with generic type parameters.
     */
    public String getNameWithTypeParameters() {
        if (isGeneric()) {
            return getTypeParameters().stream()
                .map(TypeDescriptor::getNameWithTypeParameters)
                .collect(Collectors.joining(", ", getSimpleName() + "<", ">"));
        } else {
            return getSimpleName();
        }
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
    public String getSimpleName() {
        final var lastDotIdx = name.lastIndexOf('.');
        return name.substring(lastDotIdx + 1);
    }

    /**
     * Returns the package name of the type represented by this class instance.
     *
     * @return package name.
     */
    public String getPackageName() {
        final var lastDotIdx = name.lastIndexOf('.');

        if (lastDotIdx > 0) {
            return name.substring(0, lastDotIdx);
        } else {
            throw new CodeGenException(String.format("Couldn't determine package name of '%s'", name));
        }
    }
}
