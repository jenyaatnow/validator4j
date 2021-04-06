package io.github.jenyaatnow.validator4j.sample.source.nested;

import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Validatable
@Getter
@RequiredArgsConstructor
public class GenerationSourceNestedPojo {

    private final Integer id;
}
