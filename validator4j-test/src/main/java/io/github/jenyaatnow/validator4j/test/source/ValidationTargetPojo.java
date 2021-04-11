package io.github.jenyaatnow.validator4j.test.source;

import io.github.jenyaatnow.validator4j.core.Validatable;
import io.github.jenyaatnow.validator4j.test.source.nested.NestedPojo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@Getter
@Validatable
@RequiredArgsConstructor
public class ValidationTargetPojo {
    private final Integer integer;
    private final Collection<String> strings;
    private final NestedPojo nested;
    private final List<CustomTypesPojo> customTypesPojoList;
}
