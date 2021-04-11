package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

import java.util.Collection;

/**
 * Collection of validatable scalar values.
 *
 * @param <T> type of scalar value.
 */
public final class ValidatableScalarCollection<T> extends ValidatableCollection<T, ValidatableValue<T>> {

    public ValidatableScalarCollection(@NonNull String path, Collection<T> value, @NonNull ValidationContext ctx) {
        super(path, value, ctx);
    }
}
