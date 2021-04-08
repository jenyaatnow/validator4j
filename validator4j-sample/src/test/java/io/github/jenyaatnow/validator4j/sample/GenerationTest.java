package io.github.jenyaatnow.validator4j.sample;

import io.github.jenyaatnow.validator4j.util.resource.ResourceReader;
import io.github.jenyaatnow.validator4j.util.test.IntegrationTest;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@IntegrationTest
class GenerationTest {

    @ParameterizedTest
    @EnumSource(GenerationTestCase.class)
    @SneakyThrows
    void testCorrectVClassGeneration(@NonNull final GenerationTestCase testCase) {
        final var actual = ResourceReader.instance.readResourceAsString(testCase.getActual());
        final var expected = ResourceReader.instance.readResourceAsString(testCase.getExpected());

        Assertions.assertEquals(expected, actual);
    }
}
