package com.validator4j.codegen;

import com.validator4j.util.Checks;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

class PlaceholderResolver {

    private static final BinaryOperator<String> NOOP_COMBINER = (a, b) -> null;

    public String resolve(final String template, final Stream<Function<String, String>> resolvers) {
        return resolvers.reduce(
            template,
            (intermediateResult, resolver) -> resolver.apply(intermediateResult),
            NOOP_COMBINER
        );
    }

    public String resolve(final String source,
                          final String replacement,
                          final TemplatePlaceholder placeholderName)
    {
        Checks.nonNull(source, "source");
        Checks.nonNull(replacement, "replacement");
        Checks.nonNull(placeholderName, "placeholderName");

        final var placeholder = buildPlaceholder(placeholderName);
        final var result = source.replaceAll(placeholder, replacement);
        return result;
    }

    private String buildPlaceholder(final TemplatePlaceholder placeholderName) {
        return String.format("\\$\\{%s\\}", placeholderName.getValue());
    }
}
