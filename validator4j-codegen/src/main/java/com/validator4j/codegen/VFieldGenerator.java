package com.validator4j.codegen;

import java.util.stream.Stream;

final class VFieldGenerator extends CodeUnitGenerator {

    @Override
    TemplateResource supplyTemplateResource() {
        return TemplateResource.SIMPLE_V_FIELD;
    }

    @Override
    Stream<PlaceholderReplacement> supplyPlaceholderReplacements(final ValidatableType vType,
                                                                 final GetterDetails getterDetails)
    {
        return Stream.of(
            new PlaceholderReplacement(FieldTemplatePlaceholderType.V_TYPE, vType.getSimpleName()),
            new PlaceholderReplacement(FieldTemplatePlaceholderType.FIELD_NAME, getterDetails.getFieldName())
        );
    }
}
