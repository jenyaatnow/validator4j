package com.validator4j.codegen;

import lombok.NonNull;

import javax.lang.model.element.TypeElement;
import java.util.stream.Stream;

final class ImportGenerator extends AbstractCodeGenerator {

    public String generate(@NonNull final TypeElement typeElement) {
        final var template = getTemplate(TemplateResource.IMPORT);
        final var placeholderReplacement = new PlaceholderReplacement(
            ImportTemplatePlaceholderType.FULLY_QUALIFIED_CLASS_NAME,
            typeElement.getQualifiedName().toString()
        );

        final var result = resolvePlaceholders(template, Stream.of(placeholderReplacement));

        return result;
    }
}
