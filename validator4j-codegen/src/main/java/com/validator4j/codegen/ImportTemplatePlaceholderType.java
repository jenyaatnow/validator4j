package com.validator4j.codegen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum ImportTemplatePlaceholderType implements TemplatePlaceholder {

    /**
     * Fully qualified imported class name. */
    FULLY_QUALIFIED_CLASS_NAME("unit.import.fully-qualified-class-name");

    @Getter private final String value;
}
