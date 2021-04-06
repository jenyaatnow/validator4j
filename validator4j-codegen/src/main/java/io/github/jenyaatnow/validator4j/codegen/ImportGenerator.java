package io.github.jenyaatnow.validator4j.codegen;

import lombok.NonNull;

import java.util.stream.Stream;

final class ImportGenerator extends AbstractCodeGenerator {

    public String generate(@NonNull final TypeDescriptor typeDescriptor) {
        final var template = getTemplate(TemplateResource.IMPORT);
        final var placeholderReplacement = new PlaceholderReplacement(
            ImportTemplatePlaceholderType.FULLY_QUALIFIED_CLASS_NAME,
            typeDescriptor.getName()
        );

        final var result = resolvePlaceholders(template, Stream.of(placeholderReplacement));

        return result;
    }
}
