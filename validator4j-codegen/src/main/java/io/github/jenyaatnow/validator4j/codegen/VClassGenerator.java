package io.github.jenyaatnow.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public final class VClassGenerator extends AbstractCodeGenerator {

    private final AssignmentGenerator assignmentGenerator = new AssignmentGenerator();
    private final FieldGenerator fieldGenerator = new FieldGenerator();
    private final GetterGenerator getterGenerator = new GetterGenerator();
    private final ImportGenerator importGenerator = new ImportGenerator();

    public String generate(@NonNull final ExtendedTypeDescriptor typeDescriptor,
                           @NonNull final TypeMappings typeMappings)
    {
        final var sourceType = typeDescriptor.getSimpleName();
        final var packageName = typeDescriptor.getPackageName();
        final var fields = generateFields(typeDescriptor, typeMappings);
        final var imports = generateImports(typeDescriptor);
        final var getters = generateGetters(typeDescriptor, typeMappings);
        final var assignments = generateAssignments(typeDescriptor, typeMappings);

        final var placeholderReplacements = Stream.of(
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.PACKAGE, packageName),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.TYPE_ROOT, sourceType),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.IMPORTS, imports),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.FIELDS, fields),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.GETTERS, getters),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.ASSIGNMENTS, assignments)
        );

        final var result = resolvePlaceholders(getTemplate(TemplateResource.V_CLASS), placeholderReplacements);
        return result.trim();
    }

    private String generateAssignments(@NonNull final ExtendedTypeDescriptor typeDescriptor,
                                       @NonNull final TypeMappings typeMappings)
    {
        return typeDescriptor.getFields().stream()
            .map(fieldDescriptor -> CodeGenUtils
                .indent(assignmentGenerator.generate(fieldDescriptor, typeMappings), IndentLevel.LEVEL_TWO)
            ).collect(Collectors.joining(CodeGenUtils.LINE_SEPARATOR));
    }

    private String generateFields(@NonNull final ExtendedTypeDescriptor typeDescriptor,
                                  @NonNull final TypeMappings typeMappings)
    {
        return typeDescriptor.getFields().stream()
            .map(fieldDescriptor ->
                CodeGenUtils.indent(fieldGenerator.generate(fieldDescriptor, typeMappings), IndentLevel.LEVEL_ONE))
            .collect(Collectors.joining(CodeGenUtils.LINE_SEPARATOR + CodeGenUtils.LINE_SEPARATOR));
    }

    private String generateGetters(@NonNull final ExtendedTypeDescriptor typeDescriptor,
                                   @NonNull final TypeMappings typeMappings)
    {
        return typeDescriptor.getFields().stream()
            .map(fieldDescriptor ->
                CodeGenUtils.indent(getterGenerator.generate(fieldDescriptor, typeMappings), IndentLevel.LEVEL_ONE))
            .collect(Collectors.joining(CodeGenUtils.LINE_SEPARATOR + CodeGenUtils.LINE_SEPARATOR));
    }

    private String generateImports(@NonNull final ExtendedTypeDescriptor typeDescriptor) {
        return typeDescriptor.getImports().stream()
            .map(importGenerator::generate)
            .sorted()
            .collect(Collectors.joining(CodeGenUtils.LINE_SEPARATOR));
    }
}
