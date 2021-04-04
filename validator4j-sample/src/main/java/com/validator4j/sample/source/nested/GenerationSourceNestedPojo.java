package com.validator4j.sample.source.nested;

import com.validator4j.core.Validatable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Validatable
@Getter
@RequiredArgsConstructor
public class GenerationSourceNestedPojo {

    private final Integer id;
}
