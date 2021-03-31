package com.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Stream;

final class AssignmentGenerator extends GeneratorByGetter {

    @Override
    TemplateResource supplyTemplateResource() {
        return TemplateResource.SIMPLE_ASSIGNMENT;
    }

    @Override
    Stream<PlaceholderReplacement> supplyPlaceholderReplacements(@NonNull final ValidatableType vType,
                                                                 @NonNull final GetterDescriptor getterDescriptor)
    {
        return Stream.of(
            new PlaceholderReplacement(AssignmentTemplatePlaceholderType.V_TYPE, vType.getVTypeSimpleName()),
            new PlaceholderReplacement(AssignmentTemplatePlaceholderType.FIELD_NAME, getterDescriptor.getFieldName()),
            new PlaceholderReplacement(AssignmentTemplatePlaceholderType.SOURCE_GETTER_NAME, getterDescriptor.getName()),
            new PlaceholderReplacement(
                AssignmentTemplatePlaceholderType.SOURCE_TYPE,
                getterDescriptor.getEnclosingType().getSimpleName()
            )
        );
    }
}
