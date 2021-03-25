package com.validator4j.codegen;

import com.validator4j.util.Checks;

import java.util.stream.Stream;

abstract class GeneratorByGetter extends AbstractCodeGenerator {

    public String generate(final GetterDetails getterDetails) {
        Checks.nonNull(getterDetails, "getterDetails");

        final var vType = getterDetails.getVType();

        switch (vType) {
            case INTEGER: return resolvePlaceholders(vType, getterDetails);
            // TODO Collections
            // TODO User defined types
            // TODO User defined generic types

            default: throw new RuntimeException(String.format("Unsupported type '%s'", vType));
        }
    }

    private String resolvePlaceholders(final ValidatableType vType, final GetterDetails getterDetails) {
        final var template = getTemplate(supplyTemplateResource());

        final var placeholderReplacements = supplyPlaceholderReplacements(vType, getterDetails);
        final var result = resolvePlaceholders(template, placeholderReplacements);
        return result;
    }

    abstract TemplateResource supplyTemplateResource();

    abstract Stream<PlaceholderReplacement> supplyPlaceholderReplacements(final ValidatableType vType,
                                                                          final GetterDetails getterDetails);
}
