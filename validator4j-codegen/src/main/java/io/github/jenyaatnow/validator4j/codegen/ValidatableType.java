package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.core.ValidatableBoolean;
import io.github.jenyaatnow.validator4j.core.ValidatableByte;
import io.github.jenyaatnow.validator4j.core.ValidatableCollection;
import io.github.jenyaatnow.validator4j.core.ValidatableDate;
import io.github.jenyaatnow.validator4j.core.ValidatableDouble;
import io.github.jenyaatnow.validator4j.core.ValidatableFloat;
import io.github.jenyaatnow.validator4j.core.ValidatableInteger;
import io.github.jenyaatnow.validator4j.core.ValidatableLong;
import io.github.jenyaatnow.validator4j.core.ValidatableObject;
import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import io.github.jenyaatnow.validator4j.core.ValidatableShort;
import io.github.jenyaatnow.validator4j.core.ValidatableString;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Date;

@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public enum ValidatableType {

    BOOLEAN(ValidatableBoolean.class, Boolean.class),
    BYTE(ValidatableByte.class, Byte.class),
    SHORT(ValidatableShort.class, Short.class),
    INTEGER(ValidatableInteger.class, Integer.class),
    LONG(ValidatableLong.class, Long.class),
    FLOAT(ValidatableFloat.class, Float.class),
    DOUBLE(ValidatableDouble.class, Double.class),
    STRING(ValidatableString.class, String.class),
    DATE(ValidatableDate.class, Date.class),
    COLLECTION(ValidatableCollection.class, Collection.class),
    USER_TYPE(ValidatableObject.class, Object.class),
    NON_V_TYPE(null, Object.class),
    ;

    @Getter private final Class<? extends ValidatableReference> vClass;

    @Getter private final Class<?> jClass;

    public String getVTypeSimpleName() {
        return vClass.getSimpleName();
    }
}
