package com.validator4j.codegen;

import lombok.NonNull;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

class PlaceholderResolver {

    private static final BinaryOperator<String> NOOP_COMBINER = (a, b) -> null;

    public String resolve(@NonNull final String template, @NonNull final Stream<Function<String, String>> resolvers) {
        return resolvers.reduce(
            template,
            (intermediateResult, resolver) -> resolver.apply(intermediateResult),
            NOOP_COMBINER
        );
    }

    public String resolve(@NonNull final String source, @NonNull final PlaceholderReplacement placeholderReplacement) {
        final var placeholder = buildPlaceholder(placeholderReplacement.getPlaceholder());
        final var result = source.replaceAll(placeholder, placeholderReplacement.getReplacement());
        return result;
    }

    private String buildPlaceholder(@NonNull final TemplatePlaceholder placeholderName) {
        return String.format("\\$\\{%s\\}", placeholderName.getValue());
    }
}
