package io.github.jenyaatnow.validator4j.apt;

import io.github.jenyaatnow.validator4j.codegen.ExtendedTypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.FieldDescriptor;
import io.github.jenyaatnow.validator4j.codegen.TypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.VClassGenerator;
import io.github.jenyaatnow.validator4j.codegen.DataType;
import io.github.jenyaatnow.validator4j.core.Validatable;
import io.github.jenyaatnow.validator4j.core.ValidatableObject;
import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import io.github.jenyaatnow.validator4j.core.ValidationContext;
import io.github.jenyaatnow.validator4j.util.Checks;
import lombok.NonNull;
import lombok.SneakyThrows;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SupportedAnnotationTypes(Validatable.NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class ValidatableProcessor extends AbstractProcessor {

    public static final Boolean LET_OTHER_PROCESSORS_TO_DO_THE_WORK = Boolean.FALSE;

    @Override
    public boolean process(@NonNull final Set<? extends TypeElement> annotations,
                           @NonNull final RoundEnvironment roundEnv)
    {
        bootstrap();

        annotations.stream()
            .filter(annotation -> annotation.getQualifiedName().contentEquals(Validatable.class.getName()))
            .findFirst()
            .ifPresent(annotation -> generate(roundEnv));

        return LET_OTHER_PROCESSORS_TO_DO_THE_WORK;
    }

    private void bootstrap() {
        TypeUtils.init(processingEnv);
    }

    private void generate(@NonNull final RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(Validatable.class)
            .forEach(element -> generate((TypeElement) element));
    }

    private void generate(@NonNull final TypeElement annotatedClass) {
        info(String.format("Starting to generate V-class for '%s'.", annotatedClass.getQualifiedName()));

        final var fieldDescriptors = getFieldDescriptors(annotatedClass);

        final var annotatedClassTypeDescriptor = new ExtendedTypeDescriptor(
            annotatedClass.getQualifiedName().toString(),
            DataType.VALIDATABLE,
            getImports(fieldDescriptors),
            fieldDescriptors
        );

        final var vObjectGenerator = new VClassGenerator();
        final var sourceContent = vObjectGenerator.generate(annotatedClassTypeDescriptor);

        write(annotatedClass.getSimpleName(), sourceContent);

        info(String.format("Successfully generated V-class for '%s'.", annotatedClass.getQualifiedName()));
    }

    private Set<TypeDescriptor> getImports(@NonNull final List<FieldDescriptor> fieldDescriptors) {
        final var generalTypes = TypeUtils.getTypeElements(
            ValidatableObject.class,
            ValidatableReference.class,
            ValidationContext.class,
            Checks.class
        );

        final var fieldTypes = fieldDescriptors.stream()
            .flatMap(fieldDescriptor -> fieldDescriptor.getType().getAllRelatedTypes().stream())
            .flatMap(typeDescriptor -> {
                final var vType = TypeUtils.getTypeElement(typeDescriptor.getDataType().getVClass());
                final var jType = TypeUtils.getTypeElement(typeDescriptor.getName());
                return Stream.of(vType, jType);
            })
            .filter(type -> !type.getQualifiedName().toString().startsWith("java.lang"))
            .collect(Collectors.toSet());

        generalTypes.addAll(fieldTypes);

        final var importTypes = generalTypes.stream()
            .map(typeElement -> TypeUtils.getTypeDescriptor(typeElement.asType()))
            .collect(Collectors.toSet());

        final var userTypes = fieldDescriptors.stream()
            .filter(fieldDescriptor -> fieldDescriptor.getType().getDataType() == DataType.VALIDATABLE)
            .map(fieldDescriptor -> new TypeDescriptor(
                fieldDescriptor.getType().getVClassName(),
                fieldDescriptor.getType().getDataType()
            )).collect(Collectors.toSet());

        importTypes.addAll(userTypes);
        return importTypes;
    }

    private List<FieldDescriptor> getFieldDescriptors(@NonNull final TypeElement annotatedClass) {
        final var enclosingType = TypeUtils.getTypeDescriptor(annotatedClass.asType());

        final var fieldDescriptors = annotatedClass.getEnclosedElements().stream()
            .filter(element -> element.getKind() == ElementKind.FIELD)
            .map(element -> {
                final var returnType = TypeUtils.getTypeDescriptor(element.asType());
                if (checkIfFieldShouldBeIgnored(element, returnType)) return null;

                final var fieldName = element.getSimpleName().toString();
                return new FieldDescriptor(fieldName, returnType, enclosingType);
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        return fieldDescriptors;
    }

    private boolean checkIfFieldShouldBeIgnored(@NonNull final Element element,
                                                @NonNull final TypeDescriptor returnType)
    {
        if (element.getModifiers().contains(Modifier.STATIC)) {
            return true;
        }

        if (returnType.getDataType() == DataType.OTHER) {
            warn(String.format("Ignored field of unsupported type '%s'.", returnType.getName()));
            return true;
        }
        return false;
    }

    @SneakyThrows
    private void write(@NonNull final Name className, @NonNull final String sourceContent) {
        final var file = processingEnv.getFiler().createSourceFile(Validatable.GENERATED_CLASS_PREFIX + className);

        try (final var out = new PrintWriter(file.openWriter())) {
            out.print(sourceContent);
        }
    }

    private void warn(@NonNull final String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, message);
    }

    private void info(@NonNull final String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
    }
}
