package io.github.jenyaatnow.validator4j.test.source;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.ElementType;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class NestedPojo {

    private final Integer id;

    private final Collection<Integer> ids;

    private final ElementType type;
}
