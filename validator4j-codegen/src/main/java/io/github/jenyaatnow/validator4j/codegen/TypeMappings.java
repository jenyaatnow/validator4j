package io.github.jenyaatnow.validator4j.codegen;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@NoArgsConstructor
public class TypeMappings extends HashSet<TypeMapping> {

    public TypeMappings(Collection<? extends TypeMapping> c) {
        super(c);
    }

    public Optional<TypeDescriptor> getValidatableType(@NonNull final TypeDescriptor sourceType) {
        return stream()
            .filter(mapping -> mapping.getSourceType().equals(sourceType))
            .map(TypeMapping::getValidatableType)
            .findFirst();
    }
}
