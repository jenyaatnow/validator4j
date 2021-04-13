package io.github.jenyaatnow.validator4j.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * List of validatable types.
 *
 * @param <TARGET> original type of validated elements.
 * @param <VALIDATABLE> v-type of validated elements.
 */
public class V4jList<TARGET, VALIDATABLE extends ValidatableReference<TARGET>> extends ArrayList<VALIDATABLE> {

    V4jList() {}

    /**
     * Retrieves and returns the underlying validated values.
     *
     * @return validated values collection.
     */
    public final Collection<TARGET> values() {
        return stream()
            .map(ValidatableReference::get)
            .collect(Collectors.toList());
    }
}
