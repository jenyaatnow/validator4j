package com.validator4j.codegen;

import com.validator4j.core.ValidatableBoolean;
import com.validator4j.core.ValidatableByte;
import com.validator4j.core.ValidatableCollection;
import com.validator4j.core.ValidatableDouble;
import com.validator4j.core.ValidatableFloat;
import com.validator4j.core.ValidatableInteger;
import com.validator4j.core.ValidatableLong;
import com.validator4j.core.ValidatableObject;
import com.validator4j.core.ValidatableReference;
import com.validator4j.core.ValidatableShort;
import com.validator4j.core.ValidatableString;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

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
    COLLECTION(ValidatableCollection.class, Collection.class),
    USER_TYPE(ValidatableObject.class, Object.class)
    ;

    @Getter private final Class<? extends ValidatableReference> vClass;

    @Getter private final Class<?> jClass;

    public String getVTypeSimpleName() {
        return vClass.getSimpleName();
    }
}
