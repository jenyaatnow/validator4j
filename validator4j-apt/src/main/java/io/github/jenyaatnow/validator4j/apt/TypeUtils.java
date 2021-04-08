package io.github.jenyaatnow.validator4j.apt;

import io.github.jenyaatnow.validator4j.codegen.TypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.ValidatableType;
import io.github.jenyaatnow.validator4j.core.Validatable;
import io.github.jenyaatnow.validator4j.util.Checks;
import lombok.NonNull;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static TypeElement getTypeElement(@NonNull final String className) {
        checkInitialization();

        return ELEMENTS.getTypeElement(className);
    }

    public static Set<TypeElement> getTypeElements(@NonNull final Class<?>... classes) {
        checkInitialization();

        return Stream.of(classes)
            .map(clazz -> ELEMENTS.getTypeElement(clazz.getName()))
            .collect(Collectors.toSet());
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

        return new TypeDescriptor(TYPES.erasure(typeMirror).toString(), getVType(typeMirror), typeParamsDescriptors);
    }

    private static ValidatableType getVType(@NonNull final TypeMirror typeMirror) {
        if (TYPES.asElement(typeMirror).getKind() == ElementKind.ENUM) {
            return ValidatableType.VALUE;
        }

        return Arrays.stream(ValidatableType.values())
            .filter(vType -> {
                if (vType == ValidatableType.USER_TYPE) {
                    return isValidatableAnnotationPresent(typeMirror) && isAssignable(typeMirror, vType);
                }

                return isAssignable(typeMirror, vType);
            })
            .findFirst()
            .orElseThrow(() -> new RuntimeException(String.format("Unexpected type '%s'", typeMirror)));
    }

    private static boolean isValidatableAnnotationPresent(@NonNull final TypeMirror typeMirror) {
        final var annotation = TYPES.asElement(typeMirror).getAnnotation(Validatable.class);
        return annotation != null;
    }

    private static boolean isAssignable(@NonNull final TypeMirror typeMirror, @NonNull final ValidatableType vType) {
        return vType.getJClasses().stream()
            .anyMatch(
                jClass -> TYPES.isAssignable(TYPES.erasure(typeMirror), getTypeElement(jClass).asType())
            );
    }

    private static void checkInitialization() {
        Checks.nonNullCustom(TYPES, "'TYPES' is not initialized.");
        Checks.nonNullCustom(ELEMENTS, "'ELEMENTS' is not initialized.");
    }
}
