package io.github.jenyaatnow.validator4j.codegen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum FieldTemplatePlaceholderType implements TemplatePlaceholder {

    /**
     * Field type placeholder. */
    V_TYPE("unit.field.v-type"),

    /**
     * Field name placeholder. */
    FIELD_NAME("unit.field.name");

    @Getter private final String value;
}
