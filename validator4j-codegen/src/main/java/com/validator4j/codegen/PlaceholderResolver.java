package com.validator4j.codegen;

import lombok.NonNull;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

class PlaceholderResolver {

    /**
     * We do not process stream in parallel, so we don't need any combiner.
     */
    private static final BinaryOperator<String> NOOP_COMBINER = (a, b) -> null;

    /**
     * @param template template string with placeholders to resolve.
     * @param resolvers resolver functions stream. Resolver function - is a function returning the same {@code template}
     *                  with all occurrences of the specific placeholder replaced with the corresponding replacement.
     * @return the same {@code template} with all resolver functions applied to given {@code template}.
     */
    public String resolve(@NonNull final String template,
                          @NonNull final Stream<Function<String, String>> resolvers)
    {
        return resolvers.reduce(
            template,
            (intermediateResult, resolver) -> resolver.apply(intermediateResult),
            NOOP_COMBINER
        );
    }

    /**
     * @param template template string with placeholders to resolve.
     * @param placeholderReplacement single placeholder replacement configuration.
     * @return the same {@code template} with all occurrences of {@link PlaceholderReplacement#getPlaceholder()}
     *         replaced with {@link PlaceholderReplacement#getReplacement()}.
     */
    public String resolve(@NonNull final String template,
                          @NonNull final PlaceholderReplacement placeholderReplacement)
    {
        final var placeholder = buildPlaceholder(placeholderReplacement.getPlaceholder());
        final var result = template.replaceAll(placeholder, placeholderReplacement.getReplacement());
        return result;
    }

    private String buildPlaceholder(@NonNull final TemplatePlaceholder placeholderName) {
        return String.format("\\$\\{%s\\}", placeholderName.getValue());
    }
}
