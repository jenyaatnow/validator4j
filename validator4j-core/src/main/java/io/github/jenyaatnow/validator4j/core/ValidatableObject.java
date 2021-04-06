package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

import java.util.Optional;
import java.util.function.Function;

public abstract class ValidatableObject<TARGET> extends ValidatableReference<TARGET> {

    public ValidatableObject(@NonNull final String path, final TARGET value, @NonNull final ErrorsCollector errors) {
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

    /**
     * Performs constraints validation and joins it's result with errors collected by rules validation.
     *
     * @implNote At this point validation by user-defined rules happens at the moment
     *           of {@link ValidatableReference#validate(ValidationRule...)} call. Probably better to shift
     *           this validation to the moment of the invocation of this method and use
     *           {@link ValidatableReference#validate(ValidationRule...)} just to define validation rules
     *           for further use.
     *
     * @return container with entire validation errors report.
     */
    // TODO This method should be available only in root object
    public final ErrorsReport validate() {
        final var constraintViolations = ConstraintValidator.instance.validate(value);
        errors.addAll(constraintViolations);

        return (ErrorsReport) errors;
    }
}
