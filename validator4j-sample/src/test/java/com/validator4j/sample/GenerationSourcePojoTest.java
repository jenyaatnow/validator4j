package com.validator4j.sample;

import com.validator4j.util.resource.ResourceReader;
import com.validator4j.util.test.IntegrationTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@IntegrationTest
class GenerationSourcePojoTest {

    @Test
    @SneakyThrows
    void testCorrectVClassGeneration() {
        final var expected =
            ResourceReader.instance.readResourceAsString(TestTemplateResource.V_GENERATION_SOURCE_POJO_EXPECTED);

        final var actual =
            ResourceReader.instance.readResourceAsString(TestTemplateResource.V_GENERATION_SOURCE_POJO_ACTUAL);

        Assertions.assertEquals(expected, actual);
    }
}
