package com.validator4j.core;

import com.validator4j.util.Checks;

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
    public ValidatableCollection(final String path, final Collection<T> value, final ErrorsContainer errors) {
        super(path, toValidatableList(value, path, mapSimpleValue(errors)), errors);
    }

    /**
     * Objects collection constructor.
     */
    public ValidatableCollection(final String path,
                                 final Collection<T> value,
                                 final BiFunction<String, T, VT> valueMapper,
                                 final ErrorsContainer errors)
    {
        super(path, toValidatableList(value, path, valueMapper), errors);
    }

    public void forEach(final Consumer<VT> validationHandler) {
        Checks.nonNull(validationHandler, "validationHandler");

        value.forEach(validationHandler);
    }

    // TODO Implement iterable, flatMap analogue

    private static <T1, VT1 extends ValidatableReference<T1>> List<VT1> toValidatableList(
        final Collection<T1> source, final String path, final BiFunction<String, T1, VT1> valueMapper
    ) {
        Checks.nonNull(valueMapper, "valueMapper");

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
        final ErrorsContainer errors
    ) {
        return (p, v) -> {
            if (v instanceof Integer) {
                return (VT1) new ValidatableInteger(p, (Integer) v, errors);
            }

            throw new IllegalArgumentException(String.format("Value of unsupported type '%s'", v.getClass().getName()));
        };
    }
}
