package io.github.jenyaatnow.validator4j.sample;

import io.github.jenyaatnow.validator4j.util.resource.ResourcePath;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TestTemplateResource implements ResourcePath {

    V_GENERATION_SOURCE_POJO_EXPECTED("expected/v-generation-source-pojo"),
    V_GENERATION_SOURCE_POJO_ACTUAL("generated/VGenerationSourcePojo.java"),
    ;

    @Getter
    private final String relativePath;
}
