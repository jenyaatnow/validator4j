package com.validator4j.codegen;

import com.validator4j.util.Checks;

import java.util.function.Function;
import java.util.stream.Stream;

abstract class CodeUnitGenerator {

    private final PlaceholderResolver placeholderResolver = new PlaceholderResolver();

    public String generate(final ValidatableType vType, final GetterDetails getterDetails) {
        Checks.nonNull(vType, "vType");
        Checks.nonNull(getterDetails, "getterDetails");

        switch (vType) {
            case INTEGER: return resolvePlaceholders(vType, getterDetails);
            // TODO Collections
            // TODO User defined types
            // TODO User defined generic types

            default: throw new RuntimeException(String.format("Unsupported type '%s'", vType));
        }
    }

    private String resolvePlaceholders(final ValidatableType vType, final GetterDetails getterDetails) {
        final var relativePath = supplyTemplateResource().getRelativePath();
        final var template = ResourceReader.readResourceAsString(relativePath);

        final var resolvers = supplyPlaceholderReplacements(vType, getterDetails)
            .<Function<String, String>>map(placeholderRepl ->
                tmpl -> placeholderResolver.resolve(
                    tmpl,
                    new PlaceholderReplacement(placeholderRepl.getPlaceholder(), placeholderRepl.getReplacement())
                )
            );

        return placeholderResolver.resolve(template, resolvers);
    }

    abstract TemplateResource supplyTemplateResource();

    abstract Stream<PlaceholderReplacement> supplyPlaceholderReplacements(final ValidatableType vType,
                                                                          final GetterDetails getterDetails);
}
