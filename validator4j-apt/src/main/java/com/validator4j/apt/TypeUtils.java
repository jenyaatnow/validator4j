package com.validator4j.apt;

import com.validator4j.codegen.ValidatableType;
import com.validator4j.util.Checks;
import lombok.NonNull;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class TypeUtils {

    private static ProcessingEnvironment processingEnv;

    public static void initProcessingEnv(@NonNull final ProcessingEnvironment processingEnvironment) {
        processingEnv = processingEnvironment;
    }

    public static ValidatableType getVType(@NonNull final TypeMirror typeMirror) {
        checkProcessingEnvIsInitialized();

        return Arrays.stream(ValidatableType.values())
            .filter(it -> processingEnv.getTypeUtils().isAssignable(typeMirror, getTypeElement(it.getJClass()).asType()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException(String.format("Unexpected type '%s'", typeMirror.toString())));
    }

    public static TypeElement getTypeElement(@NonNull final Class<?> clazz) {
        checkProcessingEnvIsInitialized();

        return processingEnv.getElementUtils().getTypeElement(clazz.getName());
    }

    public static Set<TypeElement> getTypeElements(@NonNull final Class<?>... classes) {
        checkProcessingEnvIsInitialized();

        return Stream.of(classes)
            .map(clazz -> processingEnv.getElementUtils().getTypeElement(clazz.getName()))
            .collect(Collectors.toSet());
    }

    private static void checkProcessingEnvIsInitialized() {
        Checks.nonNullCustom(processingEnv, "'processingEnv' is not initialized.");
    }
}
