package io.github.jenyaatnow.validator4j.apt;

import io.github.jenyaatnow.validator4j.codegen.DataType;
import io.github.jenyaatnow.validator4j.codegen.TypeDescriptor;
import io.github.jenyaatnow.validator4j.core.Validatable;
import io.github.jenyaatnow.validator4j.util.Checks;
import io.github.jenyaatnow.validator4j.util.Validator4jException;
import lombok.NonNull;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

final class TypeUtils {

    private static Types TYPES;
    private static Elements ELEMENTS;

    public static void init(@NonNull final ProcessingEnvironment processingEnvironment) {
        TYPES = processingEnvironment.getTypeUtils();
        ELEMENTS = processingEnvironment.getElementUtils();
    }

    public static TypeElement getTypeElement(@NonNull final Class<?> clazz) {
        checkInitialization();

        return ELEMENTS.getTypeElement(clazz.getName());
    }

    public static TypeDescriptor getTypeDescriptor(@NonNull final TypeMirror typeMirror) {
        checkInitialization();

        final var typeParamsDescriptors = Optional.of(typeMirror)
            .filter(type -> type instanceof DeclaredType)
            .map(type -> {
                final List<? extends TypeMirror> typeArguments = ((DeclaredType) typeMirror).getTypeArguments();
                return typeArguments.stream()
                    .map(TypeUtils::getTypeDescriptor)
                    .collect(Collectors.toList());
            })
            .orElse(Collections.emptyList());

        return new TypeDescriptor(TYPES.erasure(typeMirror).toString(), getDataType(typeMirror), typeParamsDescriptors);
    }

    private static DataType getDataType(@NonNull final TypeMirror typeMirror) {
        if (TYPES.asElement(typeMirror).getKind() == ElementKind.ENUM) {
            return DataType.VALUE;
        }

        return Arrays.stream(DataType.values())
            .filter(dataType -> {
                if (dataType == DataType.VALIDATABLE) {
                    return isValidatableAnnotationPresent(typeMirror) && isAssignable(typeMirror, dataType);
                }

                return isAssignable(typeMirror, dataType);
            })
            .findFirst()
            .orElseThrow(() -> new Validator4jException(String.format("Unexpected type '%s'", typeMirror)));
    }

    private static boolean isValidatableAnnotationPresent(@NonNull final TypeMirror typeMirror) {
        return TYPES.asElement(typeMirror).getAnnotation(Validatable.class) != null;
    }

    public static boolean isAnnotationPresent(@NonNull final Element element,
                                              @NonNull final Class<? extends Annotation> annotation)
    {
        return element.getAnnotation(annotation) != null;
    }

    private static boolean isAssignable(@NonNull final TypeMirror typeMirror, @NonNull final DataType dataType) {
        return dataType.getJClasses().stream()
            .anyMatch(
                jClass -> TYPES.isAssignable(TYPES.erasure(typeMirror), getTypeElement(jClass).asType())
            );
    }

    private static void checkInitialization() {
        Checks.nonNullCustom(TYPES, "'TYPES' is not initialized.");
        Checks.nonNullCustom(ELEMENTS, "'ELEMENTS' is not initialized.");
    }
}
