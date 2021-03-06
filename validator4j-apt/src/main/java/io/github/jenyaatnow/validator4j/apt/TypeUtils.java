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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    public static TypeDescriptor getTypeDescriptor(@NonNull final TypeMirror typeMirror) {
        checkInitialization();

        final var declaredType = (DeclaredType) typeMirror;

        final var typeParamsDescriptors = Optional.of(declaredType)
            .map(type -> {
                final List<? extends TypeMirror> typeArguments = type.getTypeArguments();
                return typeArguments.stream()
                    .map(TypeUtils::getTypeDescriptor)
                    .collect(Collectors.toList());
            })
            .orElse(Collections.emptyList());

        final var qualifiedName = declaredType.asElement().toString();
        return new TypeDescriptor(qualifiedName, getDataType(typeMirror), typeParamsDescriptors);
    }

    private static DataType getDataType(@NonNull final TypeMirror typeMirror) {
        if (isEnum(typeMirror)) return DataType.SCALAR;
        if (isScalarCollection(typeMirror)) return DataType.COLLECTION;
        if (isVCollection(typeMirror)) return DataType.V_COLLECTION;
        if (isValidatableObject(typeMirror)) return DataType.VALIDATABLE;

        return Stream.of(DataType.SCALAR, DataType.V4J, DataType.OTHER)
            .filter(dataType -> isAssignable(typeMirror, dataType))
            .findFirst()
            .orElseThrow(() -> new Validator4jException(String.format("Unexpected type '%s'", typeMirror)));
    }

    private static boolean isEnum(@NonNull final TypeMirror typeMirror) {
        return TYPES.asElement(typeMirror).getKind() == ElementKind.ENUM;
    }

    private static boolean isScalarCollection(@NonNull final TypeMirror typeMirror) {
        return getSingleTypeParam((DeclaredType) typeMirror)
            .map(typeParam ->
                typeParam.getDataType() == DataType.SCALAR && isAssignable(typeMirror, DataType.COLLECTION)
            ).orElse(false);
    }

    private static boolean isVCollection(@NonNull final TypeMirror typeMirror) {
        return getSingleTypeParam((DeclaredType) typeMirror)
            .map(typeParam ->
                typeParam.getDataType() == DataType.VALIDATABLE && isAssignable(typeMirror, DataType.V_COLLECTION)
            ).orElse(false);
    }

    private static Optional<TypeDescriptor> getSingleTypeParam(@NonNull final DeclaredType typeMirror) {
        final List<? extends TypeMirror> typeArguments = typeMirror.getTypeArguments();
        if (typeArguments.size() != 1) {
            return Optional.empty();
        }

        final var typeParam = typeArguments.stream()
            .map(TypeUtils::getTypeDescriptor)
            .findFirst();

        return typeParam;
    }

    private static boolean isValidatableObject(@NonNull final TypeMirror typeMirror) {
        return isValidatableAnnotationPresent(typeMirror) && isAssignable(typeMirror, DataType.VALIDATABLE);
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
