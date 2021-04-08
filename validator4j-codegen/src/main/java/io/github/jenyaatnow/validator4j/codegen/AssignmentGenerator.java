package io.github.jenyaatnow.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Stream;

final class AssignmentGenerator extends GeneratorByField {

    @Override
    TemplateResource supplyTemplateResource() {
        return TemplateResource.SIMPLE_ASSIGNMENT;
    }

    @Override
    Stream<PlaceholderReplacement> supplyPlaceholderReplacements(@NonNull final FieldDescriptor fieldDescriptor,
                                                                 @NonNull final TypeMappings typeMappings)
    {
        return Stream.of(
            new PlaceholderReplacement(
                AssignmentTemplatePlaceholderType.FIELD_NAME,
                fieldDescriptor.getFieldName()

            ),
            new PlaceholderReplacement(
                AssignmentTemplatePlaceholderType.V_TYPE,
                generateTypeName(fieldDescriptor, typeMappings)
            ),

            new PlaceholderReplacement(
                AssignmentTemplatePlaceholderType.SOURCE_GETTER_NAME,
                fieldDescriptor.getGetterName()
            ),

            new PlaceholderReplacement(
                AssignmentTemplatePlaceholderType.SOURCE_TYPE,
                fieldDescriptor.getEnclosingType().getSimpleName()
            )
        );
    }
}
