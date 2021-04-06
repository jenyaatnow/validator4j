package io.github.jenyaatnow.validator4j.sample.source;

import io.github.jenyaatnow.validator4j.core.Validatable;
import io.github.jenyaatnow.validator4j.sample.source.nested.GenerationSourceNestedPojo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Validatable
@Getter
@RequiredArgsConstructor
public class GenerationSourcePojo {

    private final String name;

    private final Double amount;

    private final Boolean readyToGo;

    private final ArrayList<Integer> ids;

    private final GenerationSourceNestedPojo nested;
}
