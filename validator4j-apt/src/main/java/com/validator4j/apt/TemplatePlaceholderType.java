package com.validator4j.apt;

enum TemplatePlaceholderType {

    CONSTRUCTOR_ASSIGNMENTS("template.constructor.assignments"),
    FIELDS("template.fields"),
    GETTERS("template.getters"),
    IMPORT_VALIDATOR4J("template.import.validator4j"),
    IMPORT_ROOT("template.import.root"),
    IMPORT_NESTED("template.import.nested"),
    PACKAGE("template.package"),
    TYPE_ROOT("template.type.root");

    final String value;

    TemplatePlaceholderType(String value) {
        this.value = value;
    }
}
