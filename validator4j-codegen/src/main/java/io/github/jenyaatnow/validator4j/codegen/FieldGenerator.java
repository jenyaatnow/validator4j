package io.github.jenyaatnow.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Stream;

final class FieldGenerator extends GeneratorByField {

    @Override
    TemplateResource supplyTemplateResource() {
        return TemplateResource.SIMPLE_V_FIELD;
    }

    @Override
    Stream<PlaceholderReplacement> supplyPlaceholderReplacements(@NonNull final FieldDescriptor fieldDescriptor,
                                                                 @NonNull final TypeMappings typeMappings)
    {
        return Stream.of(
            new PlaceholderReplacement(FieldTemplatePlaceholderType.FIELD_NAME, fieldDescriptor.getFieldName()),
            new PlaceholderReplacement(
                FieldTemplatePlaceholderType.V_TYPE, generateTypeName(fieldDescriptor, typeMappings)
            )
        );
    }
}
