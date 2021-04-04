package com.validator4j.codegen;

import com.validator4j.core.Validatable;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class GeneratorByGetter extends AbstractCodeGenerator {

    public String generate(@NonNull final GetterDescriptor getterDescriptor) {
        final var vType = getterDescriptor.getReturnType().getVType();

        // TODO Suppose this switch/case doesn't necessary here
        switch (vType) {
            case BOOLEAN:
            case BYTE:
            case SHORT:
            case INTEGER:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case STRING:
            case COLLECTION:
            case USER_TYPE:
                return resolvePlaceholders(getterDescriptor);

            default: throw new CodeGenException(String.format("Unsupported type '%s'", vType));
        }
    }

    private String resolvePlaceholders(@NonNull final GetterDescriptor getterDescriptor) {
        final var template = getTemplate(supplyTemplateResource());

        final var placeholderReplacements = supplyPlaceholderReplacements(getterDescriptor);
        final var result = resolvePlaceholders(template, placeholderReplacements);
        return result;
    }

    String generateTypeName(@NonNull final GetterDescriptor getterDescriptor) {
        return getVTypeSimpleNameOrMapIfGeneric(
            getterDescriptor,
            type -> {
                final var simpleName = type.getVType().getVTypeSimpleName();
                final var typeParams = type.getTypeParameters().stream()
                    .map(typeDescriptor -> {
                        final var typeName = typeDescriptor.getSimpleName();
                        final var vTypeName = typeDescriptor.getVType().getVTypeSimpleName();
                        return String.format("%s, %s", typeName, vTypeName);
                    })
                    .collect(Collectors.joining(", ", "<", ">"));

                return simpleName + typeParams;
            }
        );
    }

    String generateTypeNameDiamond(@NonNull final GetterDescriptor getterDescriptor) {
        return getVTypeSimpleNameOrMapIfGeneric(
            getterDescriptor,
            type -> type.getVType().getVTypeSimpleName() + "<>"
        );
    }

    private String getVTypeSimpleNameOrMapIfGeneric(@NonNull final GetterDescriptor getterDescriptor,
                                                    @NonNull final Function<TypeDescriptor, String> mapper)
    {
        final var vType = getterDescriptor.getReturnType().getVType();

        return Optional
            .of(getterDescriptor.getReturnType())
            .filter(TypeDescriptor::isGeneric)
            .map(mapper)
            .orElseGet(() -> {
                if (vType == ValidatableType.USER_TYPE) {
                    return Validatable.GENERATED_CLASS_PREFIX + getterDescriptor.getReturnType().getSimpleName();
                } else {
                    return vType.getVTypeSimpleName();
                }
            });
    }

    abstract TemplateResource supplyTemplateResource();

    abstract Stream<PlaceholderReplacement> supplyPlaceholderReplacements(final GetterDescriptor getterDescriptor);
}
