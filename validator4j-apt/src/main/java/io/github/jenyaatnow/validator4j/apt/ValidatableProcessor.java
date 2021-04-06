package io.github.jenyaatnow.validator4j.apt;

import io.github.jenyaatnow.validator4j.codegen.ExtendedTypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.GetterDescriptor;
import io.github.jenyaatnow.validator4j.codegen.TypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.VClassGenerator;
import io.github.jenyaatnow.validator4j.codegen.ValidatableType;
import io.github.jenyaatnow.validator4j.core.ErrorsContainer;
import io.github.jenyaatnow.validator4j.core.Validatable;
import io.github.jenyaatnow.validator4j.core.ValidatableObject;
import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import io.github.jenyaatnow.validator4j.util.Checks;
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
            getImports(getterDetails),
            getterDetails
        );

        final var vObjectGenerator = new VClassGenerator();
        final var sourceContent = vObjectGenerator.generate(typeDescriptor);

        write(annotatedClass.getSimpleName(), sourceContent);
    }

    private Set<TypeDescriptor> getImports(@NonNull final List<GetterDescriptor> getterDetails) {
        final var generalTypes = TypeUtils.getTypeElements(
            ValidatableObject.class,
            ValidatableReference.class,
            Checks.class,
            ErrorsContainer.class
        );

        final var gettersTypes = getterDetails.stream()
            .flatMap(getter -> getter.getReturnType().getAllRelatedTypes().stream())
            .map(typeDescriptor -> TypeUtils.getTypeElement(typeDescriptor.getVType().getVClass()))
            .collect(Collectors.toSet());

        generalTypes.addAll(gettersTypes);

        final var importTypes = generalTypes.stream()
            .map(typeElement -> TypeUtils.getTypeDescriptor(typeElement.asType()))
            .collect(Collectors.toSet());

        final var userTypes = getterDetails.stream()
            .filter(getter -> getter.getReturnType().getVType() == ValidatableType.USER_TYPE)
            .map(getter -> new TypeDescriptor(
                getter.getReturnType().getVClassName(),
                getter.getReturnType().getVType()
            )).collect(Collectors.toSet());

        importTypes.addAll(userTypes);
        return importTypes;
    }

    private List<GetterDescriptor> getGetterDetails(@NonNull final TypeElement annotatedClass) {
        final var getterDetails = annotatedClass.getEnclosedElements().stream()
            .filter(element -> element.getKind() == ElementKind.FIELD)
            .map(element -> {
                final var fieldType = element.asType();
                final var enclosingElement = (TypeElement) element.getEnclosingElement();

                final var fieldName = element.getSimpleName().toString();
                final var returnType = TypeUtils.getTypeDescriptor(fieldType);
                final var enclosingType = TypeUtils.getTypeDescriptor(enclosingElement.asType());

                return new GetterDescriptor(fieldName, returnType, enclosingType);
            })
            .collect(Collectors.toList());
        return getterDetails;
    }

    @SneakyThrows
    private void write(@NonNull final Name className, @NonNull final String sourceContent) {
        final var file = processingEnv.getFiler().createSourceFile(Validatable.GENERATED_CLASS_PREFIX + className);

        try (final var out = new PrintWriter(file.openWriter())) {
            out.print(sourceContent);
        }
    }
}
