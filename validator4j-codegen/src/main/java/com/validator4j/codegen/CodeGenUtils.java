package com.validator4j.codegen;

import lombok.NonNull;

import java.util.Arrays;
import java.util.stream.Collectors;

final class CodeGenUtils {

    public static final String LINE_SEPARATOR = "\n";

    public static String indent(@NonNull final String target, @NonNull final IndentLevel indentLevel) {
        return Arrays.stream(target.split(LINE_SEPARATOR))
            .map(line -> indentLevel.getIndent() + line)
            .collect(Collectors.joining(LINE_SEPARATOR));
    }
}
