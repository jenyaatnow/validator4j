package io.github.jenyaatnow.validator4j.codegen;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaceholderReplacement {

    @NonNull private final TemplatePlaceholder placeholder;
    @NonNull private final String replacement;
}
