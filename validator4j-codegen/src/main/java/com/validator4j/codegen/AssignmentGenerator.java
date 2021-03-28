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
                                                                 @NonNull final GetterDetails getterDetails)
    {
        return Stream.of(
            new PlaceholderReplacement(AssignmentTemplatePlaceholderType.V_TYPE, vType.getSimpleName()),
            new PlaceholderReplacement(AssignmentTemplatePlaceholderType.FIELD_NAME, getterDetails.getFieldName()),
            new PlaceholderReplacement(AssignmentTemplatePlaceholderType.SOURCE_GETTER_NAME, getterDetails.getName()),
            new PlaceholderReplacement(
                AssignmentTemplatePlaceholderType.SOURCE_TYPE,
                getterDetails.getEnclosingType().getSimpleName().toString()
            )
        );
    }
}
