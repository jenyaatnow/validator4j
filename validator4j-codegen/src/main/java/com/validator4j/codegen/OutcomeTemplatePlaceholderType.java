package com.validator4j.codegen;

enum OutcomeTemplatePlaceholderType implements TemplatePlaceholder {

    ASSIGNMENTS("outcome.constructor.assignments"),
    FIELDS("outcome.fields"),
    GETTERS("outcome.getters"),
    IMPORTS("outcome.imports"),
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
