package com.validator4j.apt;

import com.validator4j.util.Checks;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class ElementUtils {

    private static ProcessingEnvironment processingEnv;

    public static void initProcessingEnv(ProcessingEnvironment processingEnvironment) {
        processingEnv = processingEnvironment;
    }

    public static TypeElement getTypeElement(final Class<?> clazz) {
        Checks.nonNullCustom(processingEnv, "'processingEnv' is not initialized.");
        Checks.nonNull(clazz, "clazz");

        return processingEnv.getElementUtils().getTypeElement(clazz.getName());
    }

    public static Set<TypeElement> getTypeElements(final Class<?>... classes) {
        Checks.nonNullCustom(processingEnv, "'processingEnv' is not initialized.");
        Checks.nonNull(classes, "classes");

        return Stream.of(classes)
            .map(clazz -> {
                Checks.nonNull(clazz, "clazz");
                return processingEnv.getElementUtils().getTypeElement(clazz.getName());
            })
            .collect(Collectors.toSet());
    }
}
