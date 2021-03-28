package com.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Stream;

abstract class GeneratorByGetter extends AbstractCodeGenerator {

    public String generate(@NonNull final GetterDetails getterDetails) {
        final var vType = getterDetails.getVType();

        switch (vType) {
            case BOOLEAN:
            case BYTE:
            case SHORT:
            case INTEGER:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case STRING:
                return resolvePlaceholders(vType, getterDetails);
            // TODO Collections, maps
            // TODO User defined types
            // TODO User defined generic types

            default: throw new RuntimeException(String.format("Unsupported type '%s'", vType));
        }
    }

    private String resolvePlaceholders(@NonNull final ValidatableType vType,
                                       @NonNull final GetterDetails getterDetails)
    {
        final var template = getTemplate(supplyTemplateResource());

        final var placeholderReplacements = supplyPlaceholderReplacements(vType, getterDetails);
        final var result = resolvePlaceholders(template, placeholderReplacements);
        return result;
    }

    abstract TemplateResource supplyTemplateResource();

    abstract Stream<PlaceholderReplacement> supplyPlaceholderReplacements(final ValidatableType vType,
                                                                          final GetterDetails getterDetails);
}
