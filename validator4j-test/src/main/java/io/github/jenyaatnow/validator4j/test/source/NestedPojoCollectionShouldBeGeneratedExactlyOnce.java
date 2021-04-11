package io.github.jenyaatnow.validator4j.test.source;

import io.github.jenyaatnow.validator4j.core.Validatable;
import io.github.jenyaatnow.validator4j.test.source.nested.NestedPojo;
import lombok.Getter;

import java.util.List;

@Getter
@Validatable
public class NestedPojoCollectionShouldBeGeneratedExactlyOnce {
    private List<NestedPojo> nestedPojoList;
}
