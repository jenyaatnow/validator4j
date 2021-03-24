package com.validator4j.core;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public abstract class ValidatableObject<Target> extends ValidatableReference<Target> {

    public ValidatableObject(final String path, final Target value, final ErrorsContainer errors) {
        super(path, value, errors);
    }

    protected <Source, Value> Value safeGet(final Source source, final Function<Source, Value> valueExtractor) {
        Objects.requireNonNull(path, "Value extractor must not be null");

        return Optional.ofNullable(source).map(valueExtractor).orElse(null);
    }

    protected String appendPath(final String pathPart) {
        Objects.requireNonNull(path, "Path part must not be null");

        return path.equals(PATH_ROOT)
            ? pathPart
            : path + "." + pathPart;
    }

    public ErrorsContainer getValidationResult() {
        // TODO This method should be available only in root object
        return errors;
    }
}
