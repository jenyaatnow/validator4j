package io.github.jenyaatnow.validator4j.codegen;

import lombok.NonNull;

import java.util.List;
import java.util.stream.Stream;

abstract class GeneratorByField extends AbstractCodeGenerator {

    public String generate(@NonNull final FieldDescriptor fieldDescriptor,
                           @NonNull final TypeMappings typeMappings)
    {
        final var fieldType = fieldDescriptor.getType();
        final var fieldTypeDataType = fieldType.getDataType();

        if (List.of(DataType.OTHER, DataType.V4J).contains(fieldTypeDataType)) {
            throw new CodeGenException(String.format("Unsupported type '%s'", fieldType.getName()));
        }

        return resolvePlaceholders(fieldDescriptor, typeMappings);
    }

    private String resolvePlaceholders(@NonNull final FieldDescriptor fieldDescriptor,
                                       @NonNull final TypeMappings typeMappings)
    {
        final var template = getTemplate(supplyTemplateResource());

        final var placeholderReplacements = supplyPlaceholderReplacements(fieldDescriptor, typeMappings);
        final var result = resolvePlaceholders(template, placeholderReplacements);
        return result;
    }

    String generateTypeName(@NonNull final FieldDescriptor fieldDescriptor,
                            @NonNull final TypeMappings typeMappings)
    {
        return typeMappings.getValidatableType(fieldDescriptor.getType())
            .map(TypeDescriptor::getNameWithTypeParameters)
            .orElseThrow();
    }

    abstract TemplateResource supplyTemplateResource();

    abstract Stream<PlaceholderReplacement> supplyPlaceholderReplacements(final FieldDescriptor fieldDescriptor,
                                                                          final TypeMappings typeMappings);
}
