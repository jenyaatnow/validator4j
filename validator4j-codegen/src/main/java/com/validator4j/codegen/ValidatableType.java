package com.validator4j.codegen;

import com.validator4j.core.ValidatableCollection;
import com.validator4j.core.ValidatableInteger;
import com.validator4j.core.ValidatableReference;

@SuppressWarnings("rawtypes")
public enum ValidatableType {

    INTEGER(ValidatableInteger.class),
    COLLECTION(ValidatableCollection.class);

    private final Class<? extends ValidatableReference> type;

    ValidatableType(Class<? extends ValidatableReference> type) {
        this.type = type;
    }

    public Class<? extends ValidatableReference> getType() {
        return type;
    }

    public String getSimpleName() {
        return type.getSimpleName();
    }
}
