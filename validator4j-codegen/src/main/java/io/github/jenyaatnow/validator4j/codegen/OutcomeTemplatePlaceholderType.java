package io.github.jenyaatnow.validator4j.codegen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum OutcomeTemplatePlaceholderType implements TemplatePlaceholder {

    ASSIGNMENTS("outcome.constructor.assignments"),
    FIELDS("outcome.fields"),
    GETTERS("outcome.getters"),
    IMPORTS("outcome.imports"),
    PACKAGE("outcome.package"),
    TYPE_ROOT("outcome.type.root");

    @Getter private final String value;
}
