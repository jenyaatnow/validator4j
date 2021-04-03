package com.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Stream;

final class GetterGenerator extends GeneratorByGetter {

    @Override
    TemplateResource supplyTemplateResource() {
        return TemplateResource.SIMPLE_VALUE_GETTER;
    }

    @Override
    Stream<PlaceholderReplacement> supplyPlaceholderReplacements(@NonNull final GetterDescriptor getterDescriptor) {
        return Stream.of(
            new PlaceholderReplacement(GetterTemplatePlaceholderType.NAME, getterDescriptor.getName()),
            new PlaceholderReplacement(GetterTemplatePlaceholderType.FIELD_NAME, getterDescriptor.getFieldName()),
            new PlaceholderReplacement(GetterTemplatePlaceholderType.RETURN_TYPE, generateTypeName(getterDescriptor))
        );
    }
}
