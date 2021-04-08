package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.core.ValidatableCollection;
import io.github.jenyaatnow.validator4j.core.ValidatableObject;
import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import io.github.jenyaatnow.validator4j.core.ValidatableValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public enum DataType {

    VALUE(ValidatableValue.class, Set.of(Boolean.class, Number.class, String.class, Date.class, Enum.class)),
    COLLECTION(ValidatableCollection.class, Set.of(Collection.class)),
    VALIDATABLE(ValidatableObject.class, Set.of(Object.class)),
    V4J(null, Set.of(ValidatableReference.class)),
    OTHER(null, Set.of(Object.class))
    ;

    @Getter private final Class<? extends ValidatableReference> vClass;

    @Getter private final Set<Class<?>> jClasses;

    public String getVTypeSimpleName() {
        return vClass.getSimpleName();
    }
}
