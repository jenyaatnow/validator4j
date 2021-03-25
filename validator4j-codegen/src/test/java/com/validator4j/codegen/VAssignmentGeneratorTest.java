package com.validator4j.codegen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VAssignmentGeneratorTest {

    private final VAssignmentGenerator assignmentGenerator = new VAssignmentGenerator();

    @Test
    void generateVIntegerAssignment() {
        final var getterDetails = new GetterDetails("getId", TestPojo.class);
        final var actual = assignmentGenerator.generate(ValidatableType.INTEGER, getterDetails);
        final var expected = ResourceReader
            .readResourceAsString(TestTemplateResource.SIMPLE_ASSIGNMENT.getRelativePath());

        Assertions.assertEquals(expected, actual);
    }
}
