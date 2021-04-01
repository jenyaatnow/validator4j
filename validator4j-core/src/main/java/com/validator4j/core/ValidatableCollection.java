package com.validator4j.core;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ValidatableCollection<TARGET, VTARGET extends ValidatableReference<TARGET>>
    extends ValidatableReference<List<VTARGET>>
{

    /**
     * Simple values collection constructor.
     */
    public ValidatableCollection(@NonNull final String path,
                                 final Collection<TARGET> value,
                                 @NonNull final ErrorsContainer errors)
    {
        super(path, toValidatableList(value, path, mapSimpleValue(errors)), errors);
    }

    /**
     * Objects collection constructor.
     */
    public ValidatableCollection(@NonNull final String path,
                                 final Collection<TARGET> value,
                                 @NonNull final BiFunction<String, TARGET, VTARGET> valueMapper,
                                 @NonNull final ErrorsContainer errors)
    {
        super(path, toValidatableList(value, path, valueMapper), errors);
    }

    public void forEach(@NonNull final Consumer<VTARGET> validationHandler) {
        value.forEach(validationHandler);
    }

    // TODO Implement iterable, flatMap analogue

    private static <T, V extends ValidatableReference<T>> List<V> toValidatableList(
        final Collection<T> source, @NonNull final String path, @NonNull final BiFunction<String, T, V> valueMapper
    )
    {
        return Optional.ofNullable(source)
            .map(s -> {
                final var sourceList = new ArrayList<>(s);
                return IntStream
                    .range(0, sourceList.size())
                    .mapToObj(idx -> valueMapper.apply(String.format("%s[%d]", path, idx), sourceList.get(idx)))
                    .collect(Collectors.toList());
            })
            .orElse(null);
    }

    @SuppressWarnings("unchecked")
    private static <T, V extends ValidatableReference<T>> BiFunction<String, T, V> mapSimpleValue(
        @NonNull final ErrorsContainer errors
    )
    {
        return (p, v) -> {
            if (v instanceof Integer) {
                return (V) new ValidatableInteger(p, (Integer) v, errors);
            }

            throw new IllegalArgumentException(String.format("Value of unsupported type '%s'", v.getClass().getName()));
        };
    }
}
