package io.github.jenyaatnow.validator4j.test.source.nested;

import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Validatable
@RequiredArgsConstructor
public class NestedPojo {
    private final Integer id;
}
