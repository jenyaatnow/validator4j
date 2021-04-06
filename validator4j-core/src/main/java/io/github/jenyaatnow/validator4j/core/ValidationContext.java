package io.github.jenyaatnow.validator4j.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(staticName = "getInstance")
public class ValidationContext {

    private final ErrorsCollector errors = ErrorsCollector.getErrorsCollector();

    private final Collection<Runnable> ruleActions = new ArrayList<>();
}
