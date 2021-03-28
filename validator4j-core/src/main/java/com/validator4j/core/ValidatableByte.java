package com.validator4j.core;

import lombok.NonNull;

public final class ValidatableByte extends ValidatableReference<Byte> {

    public ValidatableByte(@NonNull final String path, final Byte value, @NonNull final ErrorsContainer errors) {
        super(path, value, errors);
    }
}
