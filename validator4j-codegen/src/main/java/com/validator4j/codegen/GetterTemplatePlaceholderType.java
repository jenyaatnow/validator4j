package com.validator4j.codegen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum GetterTemplatePlaceholderType implements TemplatePlaceholder {

    /**
     * Getter return type placeholder. */
    RETURN_TYPE("unit.getter.return-type"),

    /**
     * Getter name placeholder. */
    NAME("unit.getter.name"),

    /**
     * Getter return field name placeholder. */
    FIELD_NAME("unit.getter.field-name");

    @Getter private final String value;
}
