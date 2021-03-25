package com.validator4j.codegen;

import com.validator4j.util.Checks;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.validator4j.codegen.CodeGenUtils.LINE_SEPARATOR;


final class VObjectGenerator extends AbstractCodeGenerator {

    private final AssignmentGenerator assignmentGenerator = new AssignmentGenerator();
    private final FieldGenerator fieldGenerator = new FieldGenerator();
    private final GetterGenerator getterGenerator = new GetterGenerator();
    private final ImportGenerator importGenerator = new ImportGenerator();

    public String generate(final SourceSpec sourceSpec) {
        Checks.nonNull(sourceSpec, "sourceSpec");

        final var sourceType = sourceSpec.getSourceClass().getSimpleName();
        final var fields = generateFields(sourceSpec);
        final var getters = generateGetters(sourceSpec);
        final var assignments = generateAssignments(sourceSpec);

        final var placeholderReplacements = Stream.of(
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.PACKAGE, sourceSpec.getPackageName()),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.TYPE_ROOT, sourceType),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.IMPORTS, generateImports(sourceSpec)),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.FIELDS, fields),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.GETTERS, getters),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.ASSIGNMENTS, assignments)
        );

        final var result = resolvePlaceholders(getTemplate(TemplateResource.V_OBJECT), placeholderReplacements);
        return result;
    }

    private String generateAssignments(final SourceSpec sourceSpec) {
        return sourceSpec.getGetters().stream()
            .map(getterDetails -> CodeGenUtils
                .indent(assignmentGenerator.generate(getterDetails), IndentLevel.LEVEL_TWO)
            ).collect(Collectors.joining(LINE_SEPARATOR));
    }

    private String generateFields(final SourceSpec sourceSpec) {
        return sourceSpec.getGetters().stream()
            .map(getterDetails -> CodeGenUtils.indent(fieldGenerator.generate(getterDetails), IndentLevel.LEVEL_ONE))
            .collect(Collectors.joining(LINE_SEPARATOR + LINE_SEPARATOR));
    }

    private String generateGetters(final SourceSpec sourceSpec) {
        return sourceSpec.getGetters().stream()
            .map(getterDetails -> CodeGenUtils.indent(getterGenerator.generate(getterDetails), IndentLevel.LEVEL_ONE))
            .collect(Collectors.joining(LINE_SEPARATOR + LINE_SEPARATOR));
    }

    private String generateImports(final SourceSpec sourceSpec) {
        return sourceSpec.getImports().stream()
            .map(importGenerator::generate)
            .sorted()
            .collect(Collectors.joining(LINE_SEPARATOR));
    }
}
