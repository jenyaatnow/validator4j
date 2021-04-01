package com.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.validator4j.codegen.CodeGenUtils.LINE_SEPARATOR;


public final class VClassGenerator extends AbstractCodeGenerator {

    private final AssignmentGenerator assignmentGenerator = new AssignmentGenerator();
    private final FieldGenerator fieldGenerator = new FieldGenerator();
    private final GetterGenerator getterGenerator = new GetterGenerator();
    private final ImportGenerator importGenerator = new ImportGenerator();

    public String generate(@NonNull final ExtendedTypeDescriptor typeDescriptor) {
        final var sourceType = typeDescriptor.getSimpleName();
        final var fields = generateFields(typeDescriptor);
        final var getters = generateGetters(typeDescriptor);
        final var assignments = generateAssignments(typeDescriptor);

        final var placeholderReplacements = Stream.of(
            // FIXME We can have no package
            new PlaceholderReplacement(
                OutcomeTemplatePlaceholderType.PACKAGE,
                typeDescriptor.getPackageName().orElseThrow()
            ),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.TYPE_ROOT, sourceType),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.IMPORTS, generateImports(typeDescriptor)),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.FIELDS, fields),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.GETTERS, getters),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.ASSIGNMENTS, assignments)
        );

        final var result = resolvePlaceholders(getTemplate(TemplateResource.V_CLASS), placeholderReplacements);
        return result;
    }

    private String generateAssignments(@NonNull final ExtendedTypeDescriptor typeDescriptor) {
        return typeDescriptor.getGetters().stream()
            .map(getterDetails -> CodeGenUtils
                .indent(assignmentGenerator.generate(getterDetails), IndentLevel.LEVEL_TWO)
            ).collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String generateFields(@NonNull final ExtendedTypeDescriptor typeDescriptor) {
        return typeDescriptor.getGetters().stream()
            .map(getterDetails -> CodeGenUtils.indent(fieldGenerator.generate(getterDetails), IndentLevel.LEVEL_ONE))
            .collect(Collectors.joining(LINE_SEPARATOR + LINE_SEPARATOR));
    }

    private String generateGetters(@NonNull final ExtendedTypeDescriptor typeDescriptor) {
        return typeDescriptor.getGetters().stream()
            .map(getterDetails -> CodeGenUtils.indent(getterGenerator.generate(getterDetails), IndentLevel.LEVEL_ONE))
            .collect(Collectors.joining(LINE_SEPARATOR + LINE_SEPARATOR));
    }

    private String generateImports(@NonNull final ExtendedTypeDescriptor typeDescriptor) {
        return typeDescriptor.getImports().stream()
            .map(importGenerator::generate)
            .sorted()
            .collect(Collectors.joining(LINE_SEPARATOR));
    }
}
