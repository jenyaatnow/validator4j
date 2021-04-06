package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Basic implementation of validatable collections. For each collection of user-defined {@link Validatable} types
 * will be generated an inheritor of this class.
 *
 * @param <TARGET> original type of validated collection's elements.
 * @param <VTARGET> v-type corresponding to {@code TARGET} type.
 */
public class ValidatableCollection<TARGET, VTARGET extends ValidatableReference<TARGET>>
    extends ValidatableReference<List<VTARGET>>
{

    /**
     * Used to instantiate {@link ValidatableCollection} of simple values.
     */
    public ValidatableCollection(@NonNull final String path,
                                 final Collection<TARGET> value,
                                 @NonNull final ErrorsCollector errors)
    {
        super(path, toValidatableList(value, path, mapSimpleValue(errors)), errors);
    }

    /**
     * Used to instantiate {@link ValidatableCollection} of {@link Validatable} objects.
     */
    public ValidatableCollection(@NonNull final String path,
                                 final Collection<TARGET> value,
                                 @NonNull final BiFunction<String, TARGET, VTARGET> valueMapper,
                                 @NonNull final ErrorsCollector errors)
    {
        super(path, toValidatableList(value, path, valueMapper), errors);
    }

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
        @NonNull final ErrorsCollector errors
    )
    {
        return (p, v) -> {
            if (v instanceof Integer) {
                return (V) new ValidatableInteger(p, (Integer) v, errors);
            }

            throw new IllegalArgumentException(String.format("Value of unsupported type '%s'", v.getClass().getName()));
        };
    }

    /**
     * Iterates over collection and performs validation on each element by passed handler.
     * Use it to get access to each element of this collection in form of {@link ValidatableReference} implementation
     * and perform validation using {@link ValidatableReference#validate(ValidationRule...)}. Example:
     *     <pre>
     *     {@code
     *     final VUser vUser = ...;
     *     vUser.getIds().forEach(vInt -> vInt.validate((id, reject) -> {
     *         if (id < 1) {
     *             reject.accept("Id should be positive number");
     *         }
     *     }))
     *     }
     *     </pre>
     *
     * @param validationHandler validation handler whose only argument is a {@link ValidatableReference} instance.
     */
    public final void forEach(@NonNull final Consumer<VTARGET> validationHandler) {
        value.forEach(validationHandler);
    }

    /**
     * Simplified alternative of {@link ValidatableCollection#forEach(Consumer)}. Iterates over collection and performs
     * validation on each element by passed rules directly bypassing v-type interaction. Example:
     *     <pre>
     *     {@code
     *     final VUser vUser = ...;
     *     vUser.getIds().validateEach((id, reject) -> {
     *         if (id < 1) {
     *             reject.accept("Id should be positive number");
     *         }
     *     })
     *     }
     *     </pre>
     *
     * @param validationRules validation rules.
     */
    @SafeVarargs
    public final void validateEach(@NonNull final ValidationRule<TARGET>... validationRules) {
        value.forEach(vEntry -> Arrays.stream(validationRules).forEach(vEntry::validate));
    }

    // TODO Implement iterable, flatMap analogue
}
