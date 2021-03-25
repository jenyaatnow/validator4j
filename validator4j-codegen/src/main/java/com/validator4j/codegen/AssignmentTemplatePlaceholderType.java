package com.validator4j.codegen;

enum AssignmentTemplatePlaceholderType implements TemplatePlaceholder {

    /**
     * Assigned implementation of {@link com.validator4j.core.ValidatableReference} placeholder. */
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

    private final String value;

    AssignmentTemplatePlaceholderType(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
