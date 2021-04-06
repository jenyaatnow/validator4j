package io.github.jenyaatnow.validator4j.codegen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum IndentLevel {
    LEVEL_ONE("    "),
    LEVEL_TWO("        "),
    LEVEL_THREE("            ");

    @Getter private final String indent;
}
