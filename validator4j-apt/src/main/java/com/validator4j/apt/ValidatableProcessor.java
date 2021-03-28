package com.validator4j.apt;

import com.validator4j.codegen.GetterDetails;
import com.validator4j.codegen.SourceSpec;
import com.validator4j.codegen.VObjectGenerator;
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
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("com.validator4j.core.Validatable")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class ValidatableProcessor extends AbstractProcessor {

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
        TypeUtils.initProcessingEnv(processingEnv);
    }

    private void generate(@NonNull final RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(Validatable.class)
            .forEach(element -> generate((TypeElement) element));
    }

    private void generate(@NonNull final TypeElement annotatedClass) {
        final var getterDetails = getGetterDetails(annotatedClass);
        final var sourceSpec = new SourceSpec(
            getPackageName(annotatedClass),
            getImports(annotatedClass, getterDetails),
            annotatedClass,
            getterDetails
        );

        final var vObjectGenerator = new VObjectGenerator();
        final var sourceContent = vObjectGenerator.generate(sourceSpec);

        write(annotatedClass.getSimpleName(), sourceContent);
    }

    private Set<TypeElement> getImports(@NonNull final TypeElement annotatedClassType,
                                        @NonNull final List<GetterDetails> getterDetails)
    {
        final var requiredImportTypes = TypeUtils.getTypeElements(
            ValidatableObject.class,
            ValidatableReference.class,
            Checks.class,
            ErrorsContainer.class
        );

        final var gettersImportTypes = getterDetails.stream()
            .map(it -> TypeUtils.getTypeElement(it.getVType().getVClass()))
            .collect(Collectors.toSet());

        requiredImportTypes.add(annotatedClassType);
        requiredImportTypes.addAll(gettersImportTypes);

        return requiredImportTypes;
    }

    private List<GetterDetails> getGetterDetails(@NonNull final TypeElement annotatedClass) {
        final var getterDetails = annotatedClass.getEnclosedElements().stream()
            .filter(element -> element.getSimpleName().toString().startsWith("get")) // TODO Check getter correctness: method with no args
            .map(element -> {
                final var executableType = (ExecutableType) element.asType();
                final var returnType = executableType.getReturnType();

                return new GetterDetails(
                    element.getSimpleName().toString(),
                    TypeUtils.getVType(returnType),
                    (TypeElement) element.getEnclosingElement()
                );
            })
            .collect(Collectors.toList());
        return getterDetails;
    }

    private String getPackageName(@NonNull final TypeElement annotatedClass) {
        final var className = annotatedClass.getQualifiedName().toString();
        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }
        return packageName;
    }

    @SneakyThrows
    private void write(@NonNull final Name className, @NonNull final String sourceContent) {
        final var file = processingEnv.getFiler().createSourceFile("V" + className);

        try (final var out = new PrintWriter(file.openWriter())) {
            out.print(sourceContent);
        }
    }
}
