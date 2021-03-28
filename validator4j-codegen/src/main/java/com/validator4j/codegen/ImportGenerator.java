package com.validator4j.codegen;

import com.validator4j.util.Checks;

import javax.lang.model.element.TypeElement;
import java.util.stream.Stream;

final class ImportGenerator extends AbstractCodeGenerator {

    public String generate(final TypeElement typeElement) {
        Checks.nonNull(typeElement, "typeElement");

        final var template = getTemplate(TemplateResource.IMPORT);
        final var placeholderReplacement = new PlaceholderReplacement(
            ImportTemplatePlaceholderType.FULLY_QUALIFIED_CLASS_NAME,
            typeElement.getQualifiedName().toString()
        );

        final var result = resolvePlaceholders(template, Stream.of(placeholderReplacement));

        return result;
    }
}
