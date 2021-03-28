package com.validator4j.codegen;

import lombok.NonNull;

import java.util.function.Function;
import java.util.stream.Stream;

abstract class AbstractCodeGenerator {

    private final PlaceholderResolver placeholderResolver = new PlaceholderResolver();

    final String resolvePlaceholders(@NonNull final String template,
                                     @NonNull final Stream<PlaceholderReplacement> placeholderReplacements)
    {
        final var resolvers = placeholderReplacements
            .<Function<String, String>>map(placeholderRepl ->
                tmpl -> placeholderResolver.resolve(
                    tmpl,
                    new PlaceholderReplacement(placeholderRepl.getPlaceholder(), placeholderRepl.getReplacement())
                )
            );

        return placeholderResolver.resolve(template, resolvers);
    }

    final String getTemplate(@NonNull final TemplateResource templateResource) {
        return ResourceReader.instance.readResourceAsString(templateResource);
    }
}
