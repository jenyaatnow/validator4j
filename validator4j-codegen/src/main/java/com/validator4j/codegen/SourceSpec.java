package com.validator4j.codegen;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.lang.model.element.TypeElement;
import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class SourceSpec {

    @NonNull private final String packageName;
    @NonNull private final Collection<TypeElement> imports;
    @NonNull private final TypeElement sourceType;
    @NonNull private final List<GetterDetails> getters;
}
