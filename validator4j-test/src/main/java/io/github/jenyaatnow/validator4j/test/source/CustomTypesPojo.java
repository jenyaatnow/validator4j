package io.github.jenyaatnow.validator4j.test.source;

import io.github.jenyaatnow.validator4j.core.V4jIgnore;
import io.github.jenyaatnow.validator4j.core.Validatable;
import io.github.jenyaatnow.validator4j.test.source.nested.NestedPojo;
import lombok.Getter;

@Getter
@Validatable
public class CustomTypesPojo {
    @V4jIgnore
    private NestedPojo nestedIgnored;
    private static NestedPojo staticIgnored;

    private NestedPojo nested;
}
