package com.validator4j.codegen;

import com.validator4j.codegen.model.TypeElementImpl;
import com.validator4j.core.ErrorsContainer;
import com.validator4j.core.ValidatableInteger;
import com.validator4j.core.ValidatableObject;
import com.validator4j.core.ValidatableReference;
import com.validator4j.util.Checks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class VClassGenerationTest {

    private final VObjectGenerator generator = new VObjectGenerator();

    @Test
    void generate() {
        final var packageName = "com.validator4j.sample.generated";
        final var sourceClass = TypeElementImpl.of(TestPojo.class);

        final var getters = List.of(
            new GetterDetails("getId", ValidatableType.INTEGER, sourceClass),
            new GetterDetails("getAnotherId", ValidatableType.INTEGER, sourceClass)
        );

        final var imports = Set.of(
            ErrorsContainer.class,
            ValidatableInteger.class,
            ValidatableObject.class,
            ValidatableReference.class,
            TestPojo.class,
            Checks.class
        ).stream()
            .map(TypeElementImpl::of)
            .collect(Collectors.toSet());

        final var sourceSpec = new SourceSpec(packageName, imports, sourceClass, getters);

        final var actual = generator.generate(sourceSpec);
        final var expected = ResourceReader.instance.readResourceAsString(TestTemplateResource.V_OBJECT);

        Assertions.assertEquals(expected, actual);
    }
}
