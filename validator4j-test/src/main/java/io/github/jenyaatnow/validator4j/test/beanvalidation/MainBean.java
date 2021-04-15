package io.github.jenyaatnow.validator4j.test.beanvalidation;

import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Validatable
@RequiredArgsConstructor
public class MainBean {

    @NotNull
    private final Integer id;

    @Valid
    private final NestedBean nested;
}
