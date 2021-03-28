package com.validator4j.codegen;

import com.validator4j.core.ValidatableCollection;
import com.validator4j.core.ValidatableInteger;
import com.validator4j.core.ValidatableReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public enum ValidatableType {

    INTEGER(ValidatableInteger.class),
    COLLECTION(ValidatableCollection.class);

    @Getter private final Class<? extends ValidatableReference> type;

    public String getSimpleName() {
        return type.getSimpleName();
    }
}
