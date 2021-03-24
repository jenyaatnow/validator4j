package com.validator4j.core;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class ValidatableReference<Target> {

    protected static final String PATH_ROOT = "";

    protected final String path;

    protected final Target value;

    protected final ErrorsContainer errors;

    private final Consumer<String> reject;

    protected ValidatableReference(final String path, final Target value, final ErrorsContainer errors) {
        Objects.requireNonNull(path, "Value path must not be null");
        Objects.requireNonNull(errors, "Errors container must not be null");

        this.path = path;
        this.value = value;
        this.errors = errors;

        this.reject = message -> {
            final var error = ValidationError.of(path, message);
            this.errors.add(error);
        };
    }

    public void validate(final BiConsumer<Target, Consumer<String>> validationHandler) {
        Objects.requireNonNull(path, "Validation handler must not be null");

        validationHandler.accept(value, reject);
    }
}
