package io.github.jenyaatnow.validator4j.core;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;
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
 * @param <VALIDATABLE> v-type of validated collection's elements.
 */
public abstract class ValidatableCollection<TARGET, VALIDATABLE extends ValidatableReference<TARGET>>
    extends ValidatableReference<V4jList<TARGET, VALIDATABLE>>
{

    /**
     * Used to instantiate {@link ValidatableCollection} of simple values.
     */
    @SuppressWarnings("unchecked")
    public ValidatableCollection(@NonNull final String path,
                                 final Collection<TARGET> value,
                                 @NonNull final ValidationContext ctx)
    {
        super(path, (V4jList<TARGET, VALIDATABLE>) toValidatableList(value, path, mapSimpleValue(ctx)), ctx);
    }

    /**
     * Used to instantiate {@link ValidatableCollection} of {@link Validatable} objects.
     */
    @SuppressWarnings("unchecked")
    public ValidatableCollection(@NonNull final String path,
                                 final Collection<TARGET> value,
                                 @NonNull final BiFunction<String, TARGET, ValidatableReference<TARGET>> valueMapper,
                                 @NonNull final ValidationContext ctx)
    {
        super(path, (V4jList<TARGET, VALIDATABLE>) toValidatableList(value, path, valueMapper), ctx);
    }

    private static <T, V extends ValidatableReference<T>> V4jList<T, V> toValidatableList(
        final Collection<T> source, @NonNull final String path, @NonNull final BiFunction<String, T, V> valueMapper
    )
    {
        return Optional.ofNullable(source)
            .map(s -> {
                final var sourceList = new ArrayList<>(s);
                return IntStream
                    .range(0, sourceList.size())
                    .mapToObj(idx -> valueMapper.apply(String.format("%s[%d]", path, idx), sourceList.get(idx)))
                    .collect(Collectors.toCollection(V4jList::new));
            })
            .orElse(null);
    }

    private static <T> BiFunction<String, T, ValidatableReference<T>> mapSimpleValue(final ValidationContext ctx) {
        return (p, v) -> new ValidatableValue<>(p, v, ctx);
    }

    /**
     * Iterates over collection and prepares validation rules for each element with passed handler.
     * Use it to get access to each element of this collection in form of {@link ValidatableReference} implementation
     * and perform validation using {@link ValidatableReference#validate(ValidationRule)}. Doesn't perform
     * any validation itself. Example:
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
    public final void forEach(@NonNull final Consumer<VALIDATABLE> validationHandler) {
        value.forEach(validationHandler);
    }

    /**
     * Simplified alternative of {@link ValidatableCollection#forEach(Consumer)}. Iterates over collection and prepares
     * validation rule for each element with passed rule directly bypassing v-type interaction. Doesn't perform
     * any validation itself. Example:
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
     * @param validationRule validation rules.
     */
    public final void validateEach(@NonNull final ValidationRule<TARGET> validationRule) {
        value.forEach(vEntry -> vEntry.validate(validationRule));
    }

    /**
     * Retrieves and returns the underlying validated values of this collection.
     *
     * @return validated values collection.
     */
    public final Collection<TARGET> getValues() {
        return get().values();
    }

    // TODO Implement iterable, flatMap analogue
}
