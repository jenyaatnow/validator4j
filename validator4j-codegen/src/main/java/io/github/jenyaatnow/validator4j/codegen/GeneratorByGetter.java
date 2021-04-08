package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class GeneratorByGetter extends AbstractCodeGenerator {

    public String generate(@NonNull final GetterDescriptor getterDescriptor) {
        final var returnType = getterDescriptor.getReturnType();
        final var vType = returnType.getVType();

        if (vType == ValidatableType.NON_V_TYPE) {
            throw new CodeGenException(String.format("Unsupported type '%s'", returnType.getName()));
        }

        return resolvePlaceholders(getterDescriptor);
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

                if (type.getVType() == ValidatableType.VALUE) {
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
            .filter(typeDescriptor -> typeDescriptor.isGeneric() || typeDescriptor.getVType() == ValidatableType.VALUE)
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
