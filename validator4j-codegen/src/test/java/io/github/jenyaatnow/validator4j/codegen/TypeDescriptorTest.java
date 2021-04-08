package io.github.jenyaatnow.validator4j.codegen;

import io.github.jenyaatnow.validator4j.codegen.testutils.TypeDescriptors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TypeDescriptorTest {

    private static final String fullName = "io.github.jenyaatnow.validator4j.test.TestClass";

    @Test
    void testGetPackageName() {
        final var typeDescriptor = new TypeDescriptor(fullName, DataType.OTHER);
        Assertions.assertEquals("io.github.jenyaatnow.validator4j.test", typeDescriptor.getPackageName());
    }

    @Test
    void testGetPackageNameFailure() {
        final var typeDescriptor = new TypeDescriptor("TestClass", DataType.OTHER);
        Assertions.assertThrows(CodeGenException.class, typeDescriptor::getPackageName);

        final var typeDescriptor1 = new TypeDescriptor(".TestClass", DataType.OTHER);
        Assertions.assertThrows(CodeGenException.class, typeDescriptor1::getPackageName);
    }

    @Test
    void testGetSimpleName() {
        final var typeDescriptor = new TypeDescriptor(fullName, DataType.OTHER);
        Assertions.assertEquals("TestClass", typeDescriptor.getSimpleName());
    }

    @Test
    void testNonGenericType() {
        final var typeDescriptor = new TypeDescriptor(fullName, DataType.OTHER);
        Assertions.assertFalse(typeDescriptor.isGeneric());
    }

    @Test
    void testGenericType() {
        final var typeDescriptor =
            new TypeDescriptor(fullName, DataType.OTHER, List.of(TypeDescriptors.INTEGER));
        Assertions.assertTrue(typeDescriptor.isGeneric());
    }
}
