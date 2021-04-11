package io.github.jenyaatnow.validator4j.codegen;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * Set of {@link TypeMapping}.
 */
@NoArgsConstructor
public class TypeMappings extends HashSet<TypeMapping> {

    public TypeMappings(Collection<? extends TypeMapping> c) {
        super(c);
    }

    /**
     * Returns the validatable counterpart of passed type if present in this set.
     *
     * @param sourceType source type descriptor.
     * @return validatable type descriptor.
     */
    public Optional<TypeDescriptor> getValidatableType(@NonNull final TypeDescriptor sourceType) {
        return stream()
            .filter(mapping -> mapping.getSourceType().equals(sourceType))
            .map(TypeMapping::getValidatableType)
            .findFirst();
    }
}
