package com.validator4j.codegen;

enum GetterTemplatePlaceholderType implements TemplatePlaceholder {

    /**
     * Getter return type placeholder. */
    RETURN_TYPE("unit.getter.return-type"),

    /**
     * Getter name suffix placeholder (get[NamePart]). */
    METHOD_NAME_SUFFIX("unit.getter.method-name-suffix"),

    /**
     * Getter return field name. */
    FIELD_NAME("unit.getter.field-name");

    private final String value;

    GetterTemplatePlaceholderType(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
