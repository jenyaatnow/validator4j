package com.validator4j.core;

import lombok.NonNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class ValidatableReference<TARGET> {

    protected static final String PATH_ROOT = "";

    protected final String path;

    protected final TARGET value;

    protected final ErrorsContainer errors;

    private final Consumer<String> reject;

    protected ValidatableReference(@NonNull final String path,
                                   final TARGET value,
                                   @NonNull final ErrorsContainer errors)
    {
        this.path = path;
        this.value = value;
        this.errors = errors;

        this.reject = message -> {
            final var error = ValidationError.of(path, message);
            this.errors.add(error);
        };
    }

    public void validate(@NonNull final BiConsumer<TARGET, Consumer<String>> validationHandler) {
        validationHandler.accept(value, reject);
    }
}
