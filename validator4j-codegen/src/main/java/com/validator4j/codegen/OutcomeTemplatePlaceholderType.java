package com.validator4j.codegen;

enum OutcomeTemplatePlaceholderType implements TemplatePlaceholder {

    CONSTRUCTOR_ASSIGNMENTS("outcome.constructor.assignments"),
    FIELDS("outcome.fields"),
    GETTERS("outcome.getters"),
    IMPORT_VALIDATOR4J("outcome.import.validator4j"),
    IMPORT_JAVA("outcome.import.java"),
    IMPORT_ROOT("outcome.import.root"),
    IMPORT_NESTED("outcome.import.nested"),
    PACKAGE("outcome.package"),
    TYPE_ROOT("outcome.type.root");

    private final String value;

    OutcomeTemplatePlaceholderType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
