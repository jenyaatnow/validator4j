package com.validator4j.apt;

import com.validator4j.codegen.ValidatableType;
import com.validator4j.core.Validatable;
import com.validator4j.util.Checks;
import lombok.NonNull;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Arrays;
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

    public static ValidatableType getVType(@NonNull final TypeMirror typeMirror) {
        checkInitialization();

        return Arrays.stream(ValidatableType.values())
            .filter(vType -> {
                if (vType == ValidatableType.USER_TYPE) {
                    return isValidatableAnnotationPresent(typeMirror) && isAssignable(typeMirror, vType);
                }

                return isAssignable(typeMirror, vType);
            })
            .findFirst()
            .orElseThrow(() -> new RuntimeException(String.format("Unexpected type '%s'", typeMirror.toString())));
    }

    private static boolean isValidatableAnnotationPresent(@NonNull final TypeMirror typeMirror) {
        final var annotation = TYPES.asElement(typeMirror).getAnnotation(Validatable.class);
        return annotation != null;
    }

    private static boolean isAssignable(@NonNull final TypeMirror typeMirror, @NonNull final ValidatableType vType) {
        return TYPES.isAssignable(TYPES.erasure(typeMirror), getTypeElement(vType.getJClass()).asType());
    }

    public static TypeElement getTypeElement(@NonNull final Class<?> clazz) {
        checkInitialization();

        return ELEMENTS.getTypeElement(clazz.getName());
    }

    public static Set<TypeElement> getTypeElements(@NonNull final Class<?>... classes) {
        checkInitialization();

        return Stream.of(classes)
            .map(clazz -> ELEMENTS.getTypeElement(clazz.getName()))
            .collect(Collectors.toSet());
    }

    private static void checkInitialization() {
        Checks.nonNullCustom(TYPES, "'TYPES' is not initialized.");
        Checks.nonNullCustom(ELEMENTS, "'ELEMENTS' is not initialized.");
    }
}
