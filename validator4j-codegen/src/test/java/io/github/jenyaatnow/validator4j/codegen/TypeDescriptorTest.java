package io.github.jenyaatnow.validator4j.codegen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Test
    void getAllRelatedTypes() {
        final var optional =
            new TypeDescriptor(Optional.class.getName(), DataType.OTHER, List.of(TypeDescriptors.STRING));

        final var list =
            new TypeDescriptor(List.class.getName(), DataType.COLLECTION, List.of(optional));

        final var hashMap =
            new TypeDescriptor(HashMap.class.getName(), DataType.OTHER, List.of(TypeDescriptors.INTEGER, list));

        final var types = hashMap.getAllRelatedTypes();

        Assertions.assertEquals(5, types.size());
        Assertions.assertIterableEquals(
            new HashSet<>(Set.of(optional, list, hashMap, TypeDescriptors.STRING, TypeDescriptors.INTEGER)),
            types
        );
    }

    @Test
    void getNameWithTypeParameters() {
        final var optional =
            new TypeDescriptor(Optional.class.getName(), DataType.OTHER, List.of(TypeDescriptors.STRING));

        final var list =
            new TypeDescriptor(List.class.getName(), DataType.COLLECTION, List.of(optional));

        final var hashMap =
            new TypeDescriptor(HashMap.class.getName(), DataType.OTHER, List.of(TypeDescriptors.INTEGER, list));

        Assertions.assertEquals("HashMap<Integer, List<Optional<String>>>", hashMap.getNameWithTypeParameters());
    }
}
