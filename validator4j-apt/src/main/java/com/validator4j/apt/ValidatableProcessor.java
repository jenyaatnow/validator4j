package com.validator4j.apt;

import com.validator4j.codegen.ExtendedTypeDescriptor;
import com.validator4j.codegen.GetterDescriptor;
import com.validator4j.codegen.TypeDescriptor;
import com.validator4j.codegen.VClassGenerator;
import com.validator4j.codegen.ValidatableType;
import com.validator4j.core.ErrorsContainer;
import com.validator4j.core.Validatable;
import com.validator4j.core.ValidatableObject;
import com.validator4j.core.ValidatableReference;
import com.validator4j.util.Checks;
import lombok.NonNull;
import lombok.SneakyThrows;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes(Validatable.NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class ValidatableProcessor extends AbstractProcessor {

    private static final String GENERATED_CLASS_PREFIX = "V";

    @Override
    public boolean process(@NonNull final Set<? extends TypeElement> annotations,
                           @NonNull final RoundEnvironment roundEnv)
    {
        bootstrap();

        annotations.stream()
            .filter(annotation -> annotation.getQualifiedName().contentEquals(Validatable.class.getName()))
            .findFirst()
            .ifPresent(annotation -> generate(roundEnv));

        return false;
    }

    private void bootstrap() {
        TypeUtils.init(processingEnv);
    }

    private void generate(@NonNull final RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(Validatable.class)
            .forEach(element -> generate((TypeElement) element));
    }

    private void generate(@NonNull final TypeElement annotatedClass) {
        final var getterDetails = getGetterDetails(annotatedClass);

        final var typeDescriptor = new ExtendedTypeDescriptor(
            annotatedClass.getQualifiedName().toString(),
            ValidatableType.USER_TYPE,
            getImports(annotatedClass, getterDetails),
            getterDetails
        );

        final var vObjectGenerator = new VClassGenerator();
        final var sourceContent = vObjectGenerator.generate(typeDescriptor);

        write(annotatedClass.getSimpleName(), sourceContent);
    }

    private Set<TypeDescriptor> getImports(@NonNull final TypeElement annotatedClassType,
                                           @NonNull final List<GetterDescriptor> getterDetails)
    {
        final var requiredImportTypes = TypeUtils.getTypeElements(
            ValidatableObject.class,
            ValidatableReference.class,
            Checks.class,
            ErrorsContainer.class
        );

        final var gettersImportTypes = getterDetails.stream()
            .map(it -> TypeUtils.getTypeElement(it.getReturnType().getVType().getVClass()))
            .collect(Collectors.toSet());

        requiredImportTypes.addAll(gettersImportTypes);

        final var importTypes = requiredImportTypes.stream()
            .map(typeElement -> new TypeDescriptor(
                typeElement.getQualifiedName().toString(),
                TypeUtils.getVType(typeElement.asType())
            ))
            .collect(Collectors.toSet());

        return importTypes;
    }

    private List<GetterDescriptor> getGetterDetails(@NonNull final TypeElement annotatedClass) {
        final var getterDetails = annotatedClass.getEnclosedElements().stream()
            .filter(element -> element.getKind() == ElementKind.FIELD)
            .map(element -> {
                final var fieldType = element.asType();
                final var enclosingElement = (TypeElement) element.getEnclosingElement();

                final var fieldName = element.getSimpleName().toString();
                final var returnType = new TypeDescriptor(
                    fieldType.toString(),
                    TypeUtils.getVType(fieldType)
                );

                final var enclosingType = new TypeDescriptor(
                    enclosingElement.getQualifiedName().toString(),
                    TypeUtils.getVType(enclosingElement.asType())
                );

                return new GetterDescriptor(fieldName, returnType, enclosingType);
            })
            .collect(Collectors.toList());
        return getterDetails;
    }

    @SneakyThrows
    private void write(@NonNull final Name className, @NonNull final String sourceContent) {
        final var file = processingEnv.getFiler().createSourceFile(GENERATED_CLASS_PREFIX + className);

        try (final var out = new PrintWriter(file.openWriter())) {
            out.print(sourceContent);
        }
    }
}
