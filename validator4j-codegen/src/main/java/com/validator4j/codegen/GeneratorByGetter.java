package com.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Stream;

abstract class GeneratorByGetter extends AbstractCodeGenerator {

    public String generate(@NonNull final GetterDescriptor getterDescriptor) {
        final var vType = getterDescriptor.getReturnType().getVType();

        switch (vType) {
            case BOOLEAN:
            case BYTE:
            case SHORT:
            case INTEGER:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case STRING:
            case COLLECTION:
                return resolvePlaceholders(vType, getterDescriptor);
            // TODO Collections, maps
            // TODO User defined types
            // TODO User defined generic types

            default: throw new RuntimeException(String.format("Unsupported type '%s'", vType));
        }
    }

    private String resolvePlaceholders(@NonNull final ValidatableType vType,
                                       @NonNull final GetterDescriptor getterDescriptor)
    {
        final var template = getTemplate(supplyTemplateResource());

        final var placeholderReplacements = supplyPlaceholderReplacements(vType, getterDescriptor);
        final var result = resolvePlaceholders(template, placeholderReplacements);
        return result;
    }

    abstract TemplateResource supplyTemplateResource();

    abstract Stream<PlaceholderReplacement> supplyPlaceholderReplacements(final ValidatableType vType,
                                                                          final GetterDescriptor getterDescriptor);
}
