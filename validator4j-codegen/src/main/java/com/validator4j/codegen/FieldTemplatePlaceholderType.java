package com.validator4j.codegen;

enum FieldTemplatePlaceholderType implements TemplatePlaceholder {

    /**
     * Field type placeholder. */
    V_TYPE("unit.field.v-type"),

    /**
     * Field name placeholder. */
    FIELD_NAME("unit.field.name");

    private final String value;

    FieldTemplatePlaceholderType(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
