package com.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Stream;

final class GetterGenerator extends GeneratorByGetter {

    @Override
    TemplateResource supplyTemplateResource() {
        return TemplateResource.SIMPLE_VALUE_GETTER;
    }

    @Override
    Stream<PlaceholderReplacement> supplyPlaceholderReplacements(@NonNull final ValidatableType vType,
                                                                 @NonNull final GetterDetails getterDetails)
    {
        return Stream.of(
            new PlaceholderReplacement(GetterTemplatePlaceholderType.NAME, getterDetails.getName()),
            new PlaceholderReplacement(GetterTemplatePlaceholderType.FIELD_NAME, getterDetails.getFieldName()),
            new PlaceholderReplacement(GetterTemplatePlaceholderType.RETURN_TYPE, vType.getVTypeSimpleName())
        );
    }
}
