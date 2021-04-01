package com.validator4j.core;

import lombok.NonNull;

import java.util.Optional;
import java.util.function.Function;

public abstract class ValidatableObject<TARGET> extends ValidatableReference<TARGET> {

    public ValidatableObject(@NonNull final String path, final TARGET value, @NonNull final ErrorsContainer errors) {
        super(path, value, errors);
    }

    protected <S, V> V safeGet(final S source, @NonNull final Function<S, V> valueExtractor) {
        return Optional.ofNullable(source).map(valueExtractor).orElse(null);
    }

    protected String appendPath(@NonNull final String pathPart) {
        return path.equals(PATH_ROOT)
            ? pathPart
            : path + "." + pathPart;
    }

    public ErrorsContainer getValidationResult() {
        // TODO This method should be available only in root object
        return errors;
    }
}
