package io.github.jenyaatnow.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Entry point of validatable class source generation.
 */
public final class VClassGenerator extends AbstractCodeGenerator {

    private final FieldGenerator fieldGenerator = new FieldGenerator();
    private final GetterGenerator getterGenerator = new GetterGenerator();
    private final ImportGenerator importGenerator = new ImportGenerator();
    private final AssignmentGenerator assignmentGenerator = new AssignmentGenerator();

    /**
     * Generate the source code of the {@link io.github.jenyaatnow.validator4j.core.ValidatableObject} inheritors.
     *
     * @param typeDescriptor type of the validated class.
     * @param typeMappings set of {@link TypeMapping} used by validated class.
     * @return source code of validatable class.
     */
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

    /**
     * Generate the source code of the {@link io.github.jenyaatnow.validator4j.core.ValidatableCollection} inheritors.
     *
     * @param typeDescriptor collection content type.
     * @return source code of validatable collection.
     */
    public String generateCollection(@NonNull final ExtendedTypeDescriptor typeDescriptor) {
        final var contentType = typeDescriptor.getSingleTypeParameter();
        final var sourceType = contentType.getSimpleName();
        final var packageName = contentType.getPackageName();
        final var imports = generateImports(typeDescriptor);

        final var placeholderReplacements = Stream.of(
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.PACKAGE, packageName),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.TYPE_ROOT, sourceType),
            new PlaceholderReplacement(OutcomeTemplatePlaceholderType.IMPORTS, imports)
        );

        final var result = resolvePlaceholders(getTemplate(TemplateResource.V_COLLECTION), placeholderReplacements);
        return result.trim();
    }

    /**
     * Generates fields assignments.
     *
     * @param typeDescriptor type of the validated class.
     * @param typeMappings set of {@link TypeMapping} used by validated class.
     * @return generated fields assignments.
     */
    private String generateAssignments(@NonNull final ExtendedTypeDescriptor typeDescriptor,
                                       @NonNull final TypeMappings typeMappings)
    {
        return typeDescriptor.getFields().stream()
            .map(fieldDescriptor -> CodeGenUtils
                .indent(assignmentGenerator.generate(fieldDescriptor, typeMappings), IndentLevel.LEVEL_TWO)
            ).collect(Collectors.joining(CodeGenUtils.LINE_SEPARATOR));
    }

    /**
     * Generates fields declarations.
     *
     * @param typeDescriptor type of the validated class.
     * @param typeMappings set of {@link TypeMapping} used by validated class.
     * @return generated fields declarations.
     */
    private String generateFields(@NonNull final ExtendedTypeDescriptor typeDescriptor,
                                  @NonNull final TypeMappings typeMappings)
    {
        return typeDescriptor.getFields().stream()
            .map(fieldDescriptor ->
                CodeGenUtils.indent(fieldGenerator.generate(fieldDescriptor, typeMappings), IndentLevel.LEVEL_ONE))
            .collect(Collectors.joining(CodeGenUtils.LINE_SEPARATOR + CodeGenUtils.LINE_SEPARATOR));
    }

    /**
     * Generates getters for all validated fields.
     *
     * @param typeDescriptor type of the validated class.
     * @param typeMappings set of {@link TypeMapping} used by validated class.
     * @return generated getters.
     */
    private String generateGetters(@NonNull final ExtendedTypeDescriptor typeDescriptor,
                                   @NonNull final TypeMappings typeMappings)
    {
        return typeDescriptor.getFields().stream()
            .map(fieldDescriptor ->
                CodeGenUtils.indent(getterGenerator.generate(fieldDescriptor, typeMappings), IndentLevel.LEVEL_ONE))
            .collect(Collectors.joining(CodeGenUtils.LINE_SEPARATOR + CodeGenUtils.LINE_SEPARATOR));
    }

    /**
     * Generates import statements.
     *
     * @param typeDescriptor type of the validated class.
     * @return generated import statements.
     */
    private String generateImports(@NonNull final ExtendedTypeDescriptor typeDescriptor) {
        return typeDescriptor.getImports().stream()
            .map(importGenerator::generate)
            .sorted()
            .collect(Collectors.joining(CodeGenUtils.LINE_SEPARATOR));
    }
}
