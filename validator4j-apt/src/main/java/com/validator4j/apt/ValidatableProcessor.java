package com.validator4j.apt;

import com.validator4j.codegen.GetterDetails;
import com.validator4j.codegen.SourceSpec;
import com.validator4j.codegen.VObjectGenerator;
import com.validator4j.codegen.ValidatableType;
import com.validator4j.core.ErrorsContainer;
import com.validator4j.core.Validatable;
import com.validator4j.core.ValidatableInteger;
import com.validator4j.core.ValidatableObject;
import com.validator4j.core.ValidatableReference;
import com.validator4j.util.Checks;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("com.validator4j.core.Validatable")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class ValidatableProcessor extends AbstractProcessor {

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        bootstrap();

        annotations.stream()
            .filter(annotation -> annotation.getQualifiedName().contentEquals(Validatable.class.getName()))
            .findFirst()
            .ifPresent(annotation -> generate(roundEnv));

        return false;
    }

    private void bootstrap() {
        ElementUtils.initProcessingEnv(processingEnv);
    }

    private void generate(final RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(Validatable.class)
            .forEach(element -> generate((TypeElement) element));
    }

    private void generate(final TypeElement annotatedClass) {
        final var sourceSpec = new SourceSpec(
            getPackageName(annotatedClass),
            getImports(annotatedClass),
            annotatedClass,
            getGetterDetails(annotatedClass)
        );

        final var vObjectGenerator = new VObjectGenerator();
        final var sourceContent = vObjectGenerator.generate(sourceSpec);

        write(annotatedClass.getSimpleName(), sourceContent);
    }

    private Set<TypeElement> getImports(final TypeElement annotatedClass) {
        final var typeElements = ElementUtils.getTypeElements(
            ValidatableObject.class,
            ValidatableReference.class,
            ValidatableInteger.class,
            Checks.class,
            ErrorsContainer.class
        );

        typeElements.add(annotatedClass);

        return typeElements;
    }

    private List<GetterDetails> getGetterDetails(TypeElement annotatedClass) {
        final var getterDetails = annotatedClass.getEnclosedElements().stream()
            .filter(element -> element.getSimpleName().toString().startsWith("get"))
            .map(element ->
                new GetterDetails(
                    element.getSimpleName().toString(),
                    ValidatableType.INTEGER,
                    (TypeElement) element.getEnclosingElement()
                ))
            .collect(Collectors.toList());
        return getterDetails;
    }

    private String getPackageName(TypeElement annotatedClass) {
        final var className = annotatedClass.getQualifiedName().toString();
        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }
        return packageName;
    }

    private void write(final Name className, final String sourceContent) {
        try {
            final var file = processingEnv.getFiler().createSourceFile("V" + className);

            try (final var out = new PrintWriter(file.openWriter())) {
                out.print(sourceContent);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
