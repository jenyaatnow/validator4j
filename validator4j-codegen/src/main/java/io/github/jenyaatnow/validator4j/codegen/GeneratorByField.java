package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class GeneratorByField extends AbstractCodeGenerator {

    public String generate(@NonNull final FieldDescriptor fieldDescriptor) {
        final var returnType = fieldDescriptor.getType();
        final var returnTypeDataType = returnType.getDataType();

        if (List.of(DataType.OTHER, DataType.V4J).contains(returnTypeDataType)) {
            throw new CodeGenException(String.format("Unsupported type '%s'", returnType.getName()));
        }

        return resolvePlaceholders(fieldDescriptor);
    }

    private String resolvePlaceholders(@NonNull final FieldDescriptor fieldDescriptor) {
        final var template = getTemplate(supplyTemplateResource());

        final var placeholderReplacements = supplyPlaceholderReplacements(fieldDescriptor);
        final var result = resolvePlaceholders(template, placeholderReplacements);
        return result;
    }

    String generateTypeName(@NonNull final FieldDescriptor fieldDescriptor) {
        return getVTypeSimpleNameOrMapIfGeneric(
            fieldDescriptor,
            type -> {
                final var simpleName = type.getDataType().getVTypeSimpleName();

                if (type.getDataType() == DataType.VALUE) {
                    return simpleName + "<" + type.getSimpleName() + ">";
                }

                final var typeParams = type.getTypeParameters().stream()
                    .map(typeDescriptor -> {
                        final var typeName = typeDescriptor.getSimpleName();
                        return String.format("%s", typeName);
                    })
                    .collect(Collectors.joining(", ", "<", ">"));

                return simpleName + typeParams;
            }
        );
    }

    String generateTypeNameDiamond(@NonNull final FieldDescriptor fieldDescriptor) {
        return getVTypeSimpleNameOrMapIfGeneric(
            fieldDescriptor,
            type -> type.getDataType().getVTypeSimpleName() + "<>"
        );
    }

    private String getVTypeSimpleNameOrMapIfGeneric(@NonNull final FieldDescriptor fieldDescriptor,
                                                    @NonNull final Function<TypeDescriptor, String> mapper)
    {
        final var dataType = fieldDescriptor.getType().getDataType();

        return Optional
            .of(fieldDescriptor.getType())
            .filter(typeDescriptor -> typeDescriptor.isGeneric() || typeDescriptor.getDataType() == DataType.VALUE)
            .map(mapper)
            .orElseGet(() -> {
                if (dataType == DataType.VALIDATABLE) {
                    return Validatable.GENERATED_CLASS_PREFIX + fieldDescriptor.getType().getSimpleName();
                } else {
                    return dataType.getVTypeSimpleName();
                }
            });
    }

    abstract TemplateResource supplyTemplateResource();

    abstract Stream<PlaceholderReplacement> supplyPlaceholderReplacements(final FieldDescriptor fieldDescriptor);
}
