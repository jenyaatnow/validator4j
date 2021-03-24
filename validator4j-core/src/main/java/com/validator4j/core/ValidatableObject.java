package com.validator4j.core;

import com.validator4j.util.Checks;

import java.util.Optional;
import java.util.function.Function;

public abstract class ValidatableObject<Target> extends ValidatableReference<Target> {

    public ValidatableObject(final String path, final Target value, final ErrorsContainer errors) {
        super(path, value, errors);
    }

    protected <Source, Value> Value safeGet(final Source source, final Function<Source, Value> valueExtractor) {
        Checks.nonNull(valueExtractor, "valueExtractor");

        return Optional.ofNullable(source).map(valueExtractor).orElse(null);
    }

    protected String appendPath(final String pathPart) {
        Checks.nonNull(pathPart, "pathPart");

        return path.equals(PATH_ROOT)
            ? pathPart
            : path + "." + pathPart;
    }

    public ErrorsContainer getValidationResult() {
        // TODO This method should be available only in root object
        return errors;
    }
}
