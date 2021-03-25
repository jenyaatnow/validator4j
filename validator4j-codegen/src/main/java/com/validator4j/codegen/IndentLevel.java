package com.validator4j.codegen;

enum IndentLevel {
    LEVEL_ONE  ("    "),
    LEVEL_TWO  ("        "),
    LEVEL_THREE("            ");

    private final String indent;

    IndentLevel(String indent) {
        this.indent = indent;
    }

    public String getIndent() {
        return indent;
    }
}
