package com.validator4j.codegen;

import com.validator4j.util.Checks;

import java.util.function.Function;
import java.util.stream.Stream;

abstract class AbstractCodeGenerator {

    private final PlaceholderResolver placeholderResolver = new PlaceholderResolver();

    final String resolvePlaceholders(final String template,
                                     final Stream<PlaceholderReplacement> placeholderReplacements)
    {
        Checks.nonNull(template, "template");
        Checks.nonNull(placeholderReplacements, "placeholderReplacements");

        final var resolvers = placeholderReplacements
            .<Function<String, String>>map(placeholderRepl ->
                tmpl -> placeholderResolver.resolve(
                    tmpl,
                    new PlaceholderReplacement(placeholderRepl.getPlaceholder(), placeholderRepl.getReplacement())
                )
            );

        return placeholderResolver.resolve(template, resolvers);
    }
}
