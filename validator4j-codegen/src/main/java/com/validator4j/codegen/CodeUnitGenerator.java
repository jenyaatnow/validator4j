package com.validator4j.codegen;

import com.validator4j.util.Checks;

import java.util.stream.Stream;

abstract class CodeUnitGenerator extends AbstractCodeGenerator {

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
        final var template = ResourceReader.instance.readResourceAsString(supplyTemplateResource());

        final var placeholderReplacements = supplyPlaceholderReplacements(vType, getterDetails);
        final var result = resolvePlaceholders(template, placeholderReplacements);
        return result;
    }

    abstract TemplateResource supplyTemplateResource();

    abstract Stream<PlaceholderReplacement> supplyPlaceholderReplacements(final ValidatableType vType,
                                                                          final GetterDetails getterDetails);
}
