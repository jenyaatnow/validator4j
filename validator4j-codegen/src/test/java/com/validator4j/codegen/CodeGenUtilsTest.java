package com.validator4j.codegen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CodeGenUtilsTest {

    @Test
    void indent() {
        final var input =    "test\n"
                           + "test";

        final var expected = "    test\n"
                           + "    test";

        final var actual = CodeGenUtils.indent(input, IndentLevel.LEVEL_ONE);

        Assertions.assertEquals(expected, actual);
    }
}