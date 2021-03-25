package com.validator4j.codegen;

enum ImportTemplatePlaceholderType implements TemplatePlaceholder {

    /**
     * Fully qualified imported class name. */
    FULLY_QUALIFIED_CLASS_NAME("unit.import.fully-qualified-class-name");

    private final String value;

    ImportTemplatePlaceholderType(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
