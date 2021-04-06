package io.github.jenyaatnow.validator4j.core;

import java.util.function.Consumer;

/**
 * Represents a validation rule.
 * Accepts two arguments: validating value and {@link Consumer} that should be used to reject invalid value.
 *
 * @param <TARGET> type of validated value.
 */
@FunctionalInterface
public interface ValidationRule<TARGET> {

    /**
     * Describes the rule for validating the passed value.
     *
     * @param value validating value.
     * @param reject error message consumer that should be used to reject invalid value.
     */
    void validate(TARGET value, Consumer<String> reject);
}
