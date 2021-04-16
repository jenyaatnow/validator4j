package io.github.jenyaatnow.validator4j.apt;

import com.google.auto.service.AutoService;
import io.github.jenyaatnow.validator4j.codegen.DataType;
import io.github.jenyaatnow.validator4j.codegen.ExtendedTypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.FieldDescriptor;
import io.github.jenyaatnow.validator4j.codegen.TypeDescriptor;
import io.github.jenyaatnow.validator4j.codegen.TypeDescriptors;
import io.github.jenyaatnow.validator4j.codegen.TypeMappings;
import io.github.jenyaatnow.validator4j.codegen.VClassGenerator;
import io.github.jenyaatnow.validator4j.core.V4jIgnore;
import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(Processor.class)
@SupportedAnnotationTypes(Validatable.NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class ValidatableProcessor extends AbstractProcessor {

    public static final Boolean LET_OTHER_PROCESSORS_TO_DO_THE_WORK = Boolean.FALSE;

    private TypeUtils typeUtils;
    private final TypeMapper typeMapper = new TypeMapper();
    private final VClassGenerator generator = new VClassGenerator();

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
        typeUtils = new TypeUtils(processingEnv);
    }

    private void generate(@NonNull final RoundEnvironment roundEnv) {
        final var inputs = roundEnv.getElementsAnnotatedWith(Validatable.class).stream()
            .map(annotatedClass -> prepare((TypeElement) annotatedClass))
            .collect(Collectors.toList());

        final var typeMappings = inputs.stream()
            .flatMap(input -> input.typeMappings.stream())
            .collect(Collectors.toCollection(TypeMappings::new));

        generateCollectionClasses(typeMappings);
        inputs.forEach(this::generateValidatableClass);
    }

    private Input prepare(@NonNull final TypeElement annotatedClass) {
        final var fieldDescriptors = getFieldDescriptors(annotatedClass);
        final var typeMappings = fieldDescriptors.stream()
            .map(field -> typeMapper.mapToValidatable(field.getType()))
            .collect(Collectors.toCollection(TypeMappings::new));

        return new Input(annotatedClass, fieldDescriptors, typeMappings);
    }

    private void generateValidatableClass(@NonNull final Input input) {
        final var imports = getImports(input.typeMappings);
        final var annotatedClassTypeDescriptor = new ExtendedTypeDescriptor(
            input.annotatedClass.getQualifiedName().toString(),
            DataType.VALIDATABLE,
            imports,
            input.fieldDescriptors
        );
        final var sourceContent = generator.generate(annotatedClassTypeDescriptor, input.typeMappings);
        write(Validatable.GENERATED_CLASS_PREFIX + input.annotatedClass.getSimpleName(), sourceContent);
    }

    private void generateCollectionClasses(@NonNull final TypeMappings typeMappings) {
        typeMappings.stream()
            .filter(mapping -> mapping.getSourceType().getDataType() == DataType.V_COLLECTION)
            .distinct()
            .forEach(mapping -> {
                final var typeDescriptor = mapping.getSourceType().toExtendedTypeDescriptor(
                    Set.of(
                        TypeDescriptors.COLLECTION_OF_VALUES,
                        TypeDescriptors.VALIDATION_CONTEXT,
                        TypeDescriptors.VALIDATABLE_COLLECTION
                    ),
                    List.of()
                );
                final var sourceCode = generator.generateCollection(typeDescriptor);
                write(mapping.getValidatableType().getSimpleName(), sourceCode);
            });
    }

    private List<FieldDescriptor> getFieldDescriptors(@NonNull final TypeElement annotatedClass) {
        final var enclosingType = typeUtils.getTypeDescriptor(annotatedClass.asType());

        final var fieldDescriptors = annotatedClass.getEnclosedElements().stream()
            .filter(element -> element.getKind() == ElementKind.FIELD)
            .map(element -> {
                final var fieldType = typeUtils.getTypeDescriptor(element.asType());
                if (checkIfFieldShouldBeIgnored(enclosingType, element, fieldType)) return null;

                final var fieldName = element.getSimpleName().toString();
                return new FieldDescriptor(fieldName, fieldType, enclosingType);
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        return fieldDescriptors;
    }

    private Collection<TypeDescriptor> getImports(@NonNull final TypeMappings typeMappings) {
        final var importTypes = typeMappings.stream()
            .flatMap(mapping -> mapping.getValidatableType().getAllRelatedTypes().stream())
            .filter(type -> !type.getName().startsWith(Class.class.getPackageName()))
            .collect(Collectors.toMap(TypeDescriptor::getName, p -> p, (p, q) -> p))
            .values();

        final var generalImports = new HashSet<>(Set.of(
            TypeDescriptors.CHECKS,
            TypeDescriptors.VALIDATABLE_OBJECT,
            TypeDescriptors.VALIDATION_CONTEXT,
            TypeDescriptors.VALIDATABLE_REFERENCE
        ));

        generalImports.addAll(importTypes);

        return generalImports;
    }

    private boolean checkIfFieldShouldBeIgnored(@NonNull final TypeDescriptor enclosingElement,
                                                @NonNull final Element element,
                                                @NonNull final TypeDescriptor fieldType)
    {
        if (element.getModifiers().contains(Modifier.STATIC)) {
            return true;
        }

        if (typeUtils.isAnnotationPresent(element, V4jIgnore.class)) {
            return true;
        }

        if (fieldType.getDataType() == DataType.OTHER) {
            warn(String.format(
                "Ignored field of unsupported type '%s' [at %s].",
                fieldType.getSimpleNameWithTypeParameters(),
                enclosingElement.getName()
            ));
            return true;
        }
        return false;
    }

    @SneakyThrows
    private void write(@NonNull final CharSequence className, @NonNull final String sourceContent) {
        final var file = processingEnv.getFiler().createSourceFile(className);

        try (final var out = new PrintWriter(file.openWriter())) {
            out.print(sourceContent);
        }
    }

    private void warn(@NonNull final String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, message);
    }

    @RequiredArgsConstructor
    private static class Input {
        final TypeElement annotatedClass;
        final List<FieldDescriptor> fieldDescriptors;
        final TypeMappings typeMappings;
    }
}
