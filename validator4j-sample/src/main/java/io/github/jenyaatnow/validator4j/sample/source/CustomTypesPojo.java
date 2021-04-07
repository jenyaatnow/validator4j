package io.github.jenyaatnow.validator4j.sample.source;

import io.github.jenyaatnow.validator4j.core.Validatable;
import io.github.jenyaatnow.validator4j.sample.source.nested.NestedPojo;
import lombok.Getter;

@Getter
@Validatable
public class CustomTypesPojo {
    private NestedPojo nested;
}
