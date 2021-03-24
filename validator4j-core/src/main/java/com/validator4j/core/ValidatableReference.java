package com.validator4j.core;

import com.validator4j.util.Checks;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class ValidatableReference<Target> {

    protected static final String PATH_ROOT = "";

    protected final String path;

    protected final Target value;

    protected final ErrorsContainer errors;

    private final Consumer<String> reject;

    protected ValidatableReference(final String path, final Target value, final ErrorsContainer errors) {
        Checks.nonNull(path, "path");
        Checks.nonNull(errors, "errors");

        this.path = path;
        this.value = value;
        this.errors = errors;

        this.reject = message -> {
            final var error = ValidationError.of(path, message);
            this.errors.add(error);
        };
    }

    public void validate(final BiConsumer<Target, Consumer<String>> validationHandler) {
        Checks.nonNull(validationHandler, "validationHandler");

        validationHandler.accept(value, reject);
    }
}
