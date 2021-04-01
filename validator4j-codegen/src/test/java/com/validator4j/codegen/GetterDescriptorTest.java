package com.validator4j.codegen;

import com.validator4j.codegen.testutils.TypeDescriptors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GetterDescriptorTest {

    private static final TypeDescriptor TYPE_DESCRIPTOR = TypeDescriptors.INTEGER;

    @Test
    void incorrectGetterNameShouldCauseException() {
        Assertions.assertThrows(
            CodeGenException.class,
            () -> new GetterDescriptor("wrongName", TYPE_DESCRIPTOR, TYPE_DESCRIPTOR)
        );
    }

    @Test
    void testGetFieldName() {
        final var getterDescriptor = new GetterDescriptor("getName", TYPE_DESCRIPTOR, TYPE_DESCRIPTOR);
        Assertions.assertEquals("name", getterDescriptor.getFieldName());
    }
}
