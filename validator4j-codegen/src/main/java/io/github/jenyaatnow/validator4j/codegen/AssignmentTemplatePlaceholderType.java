package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.core.ValidatableReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum AssignmentTemplatePlaceholderType implements TemplatePlaceholder {

    /**
     * Assigned implementation of {@link ValidatableReference} placeholder. */
    V_TYPE("unit.assignment.v-type"),

    /**
     * Source object type placeholder. */
    SOURCE_TYPE("unit.assignment.source-type"),

    /**
     * Source object's getter name placeholder. */
    SOURCE_GETTER_NAME("unit.assignment.source-getter-name"),

    /**
     * Field return field name placeholder. */
    FIELD_NAME("unit.assignment.field-name");

    @Getter private final String value;
}
