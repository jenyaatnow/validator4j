package com.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Stream;

final class FieldGenerator extends GeneratorByGetter {

    @Override
    TemplateResource supplyTemplateResource() {
        return TemplateResource.SIMPLE_V_FIELD;
    }

    @Override
    Stream<PlaceholderReplacement> supplyPlaceholderReplacements(@NonNull final GetterDescriptor getterDescriptor) {
        return Stream.of(
            new PlaceholderReplacement(FieldTemplatePlaceholderType.V_TYPE, generateTypeName(getterDescriptor)),
            new PlaceholderReplacement(FieldTemplatePlaceholderType.FIELD_NAME, getterDescriptor.getFieldName())
        );
    }
}
