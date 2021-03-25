package com.validator4j.codegen;

import java.util.stream.Stream;

final class VGetterGenerator extends CodeGenerator {

    @Override
    TemplateResource supplyTemplateResource() {
        return TemplateResource.SIMPLE_VALUE_GETTER;
    }

    @Override
    Stream<PlaceholderReplacement> supplyPlaceholderReplacements(final ValidatableType vType,
                                                                 final GetterDetails getterDetails)
    {
        return Stream.of(
            new PlaceholderReplacement(GetterTemplatePlaceholderType.NAME, getterDetails.getName()),
            new PlaceholderReplacement(GetterTemplatePlaceholderType.FIELD_NAME, getterDetails.getFieldName()),
            new PlaceholderReplacement(GetterTemplatePlaceholderType.RETURN_TYPE, vType.getSimpleName())
        );
    }
}
