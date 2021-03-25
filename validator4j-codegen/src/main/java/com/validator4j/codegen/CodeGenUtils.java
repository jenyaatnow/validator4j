package com.validator4j.codegen;

import com.validator4j.util.Checks;

import java.util.Arrays;
import java.util.stream.Collectors;

final class CodeGenUtils {

    public static final String LINE_SEPARATOR = "\n";

    public static String indent(final String target, final IndentLevel indentLevel) {
        Checks.nonNull(target, "target");
        Checks.nonNull(indentLevel, "indentLevel");

        return Arrays.stream(target.split(LINE_SEPARATOR))
            .map(line -> indentLevel.getIndent() + line)
            .collect(Collectors.joining(LINE_SEPARATOR));
    }
}
