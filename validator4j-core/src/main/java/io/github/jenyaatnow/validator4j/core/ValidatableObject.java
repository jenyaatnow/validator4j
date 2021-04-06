package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

import java.util.Optional;
import java.util.function.Function;

public abstract class ValidatableObject<TARGET> extends ValidatableReference<TARGET> {

    public ValidatableObject(@NonNull final String path, final TARGET value, @NonNull final ValidationContext ctx) {
        super(path, value, ctx);
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
     * Performs validation both by constraint annotations and by passed {@link ValidationRule}s and joins it's results.
     *
     * @return container with entire validation errors report.
     */
    // TODO This method should be available only in root object
    public final ErrorsReport validate() {
        ruleActions.forEach(Runnable::run);

        final var constraintViolations = ConstraintValidator.instance.validate(value);
        errors.addAll(constraintViolations);

        return (ErrorsReport) errors;
    }
}
