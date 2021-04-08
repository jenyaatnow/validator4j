package io.github.jenyaatnow.validator4j.codegen;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TypeMapping {
    private final TypeDescriptor sourceType;
    private final TypeDescriptor validatableType;
}
