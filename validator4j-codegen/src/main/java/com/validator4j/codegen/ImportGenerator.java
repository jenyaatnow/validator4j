package com.validator4j.codegen;

import com.validator4j.util.Checks;

import java.util.stream.Stream;

final class ImportGenerator extends AbstractCodeGenerator {

    public String generate(final Class<?> clazz) {
        Checks.nonNull(clazz, "clazz");

        final var template = getTemplate(TemplateResource.IMPORT);
        final var placeholderReplacement =
            new PlaceholderReplacement(ImportTemplatePlaceholderType.FULLY_QUALIFIED_CLASS_NAME, clazz.getName());
        final var result = resolvePlaceholders(template, Stream.of(placeholderReplacement));

        return result;
    }
}
