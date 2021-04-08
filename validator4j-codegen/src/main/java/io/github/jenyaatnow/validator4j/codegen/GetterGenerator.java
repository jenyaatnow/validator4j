package io.github.jenyaatnow.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Stream;

final class GetterGenerator extends GeneratorByField {

    @Override
    TemplateResource supplyTemplateResource() {
        return TemplateResource.SIMPLE_VALUE_GETTER;
    }

    @Override
    Stream<PlaceholderReplacement> supplyPlaceholderReplacements(@NonNull final FieldDescriptor fieldDescriptor) {
        return Stream.of(
            new PlaceholderReplacement(GetterTemplatePlaceholderType.NAME, fieldDescriptor.getGetterName()),
            new PlaceholderReplacement(GetterTemplatePlaceholderType.FIELD_NAME, fieldDescriptor.getFieldName()),
            new PlaceholderReplacement(GetterTemplatePlaceholderType.RETURN_TYPE, generateTypeName(fieldDescriptor))
        );
    }
}
