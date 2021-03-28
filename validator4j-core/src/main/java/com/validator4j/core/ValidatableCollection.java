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

public class ValidatableCollection<T, VT extends ValidatableReference<T>> extends ValidatableReference<List<VT>> {

    /**
     * Simple values collection constructor.
     */
    public ValidatableCollection(@NonNull final String path,
                                 final Collection<T> value,
                                 @NonNull final ErrorsContainer errors)
    {
        super(path, toValidatableList(value, path, mapSimpleValue(errors)), errors);
    }

    /**
     * Objects collection constructor.
     */
    public ValidatableCollection(@NonNull final String path,
                                 final Collection<T> value,
                                 @NonNull final BiFunction<String, T, VT> valueMapper,
                                 @NonNull final ErrorsContainer errors)
    {
        super(path, toValidatableList(value, path, valueMapper), errors);
    }

    public void forEach(@NonNull final Consumer<VT> validationHandler) {
        value.forEach(validationHandler);
    }

    // TODO Implement iterable, flatMap analogue

    private static <T1, VT1 extends ValidatableReference<T1>> List<VT1> toValidatableList(
        final Collection<T1> source, @NonNull final String path, @NonNull final BiFunction<String, T1, VT1> valueMapper
    ) {
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
    private static <T1, VT1 extends ValidatableReference<T1>> BiFunction<String, T1, VT1> mapSimpleValue(
        @NonNull final ErrorsContainer errors
    ) {
        return (p, v) -> {
            if (v instanceof Integer) {
                return (VT1) new ValidatableInteger(p, (Integer) v, errors);
            }

            throw new IllegalArgumentException(String.format("Value of unsupported type '%s'", v.getClass().getName()));
        };
    }
}
