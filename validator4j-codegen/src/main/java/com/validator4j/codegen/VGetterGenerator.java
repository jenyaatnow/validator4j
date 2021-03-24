package com.validator4j.codegen;

import com.validator4j.core.ValidatableInteger;
import com.validator4j.util.Checks;

import java.util.function.Function;
import java.util.stream.Stream;

final class VGetterGenerator implements CodeGenerator {

    private final PlaceholderResolver placeholderResolver = new PlaceholderResolver();

    public String generateVIntegerGetter(final String fieldName) {
        Checks.nonNull(fieldName, "fieldName");

        final var relativePath = TemplateResource.SINGLE_VALUE_GETTER.getRelativePath();
        final var template = ResourceReader.readResourceAsString(relativePath);

        final var methodNameSuffix = getMethodNameSuffix(fieldName);

        final var resolvers = Stream.<Function<String, String>>of(
            (tmpl) -> placeholderResolver.resolve(
                tmpl,
                methodNameSuffix,
                GetterTemplatePlaceholderType.METHOD_NAME_SUFFIX
            ),
            (tmpl) -> placeholderResolver.resolve(
                tmpl,
                fieldName,
                GetterTemplatePlaceholderType.FIELD_NAME
            ),
            (tmpl) -> placeholderResolver.resolve(
                tmpl,
                ValidatableInteger.class.getSimpleName(),
                GetterTemplatePlaceholderType.RETURN_TYPE
            )
        );

        return placeholderResolver.resolve(template, resolvers);
    }

    private String getMethodNameSuffix(final String fieldName) {
        final var firstCharacter = fieldName.substring(0, 1).toUpperCase();
        final var rest = fieldName.substring(1);
        return firstCharacter + rest;
    }
}
