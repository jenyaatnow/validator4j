package io.github.jenyaatnow.validator4j.test.beanvalidation;

import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Getter
@Validatable
@RequiredArgsConstructor
public class NestedBean {

    private final Collection<@NotBlank String> strings;
}
