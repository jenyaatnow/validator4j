package com.validator4j.codegen;

import com.validator4j.util.Checks;

public class PlaceholderReplacement {

    private final TemplatePlaceholder placeholder;
    private final String replacement;

    public PlaceholderReplacement(TemplatePlaceholder placeholder, String replacement) {
        Checks.nonNull(placeholder, "placeholder");
        Checks.nonNull(replacement, "replacement");

        this.placeholder = placeholder;
        this.replacement = replacement;
    }

    public TemplatePlaceholder getPlaceholder() {
        return placeholder;
    }

    public String getReplacement() {
        return replacement;
    }
}
