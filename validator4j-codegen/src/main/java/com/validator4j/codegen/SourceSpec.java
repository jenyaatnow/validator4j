package com.validator4j.codegen;

import com.validator4j.util.Checks;

import java.util.Collection;
import java.util.List;

public class SourceSpec {

    private final String packageName;
    private final Collection<Class<?>> imports;
    private final Class<?> sourceClass;
    private final List<GetterDetails> getters;

    public SourceSpec(final String packageName,
                      final Collection<Class<?>> imports,
                      final Class<?> sourceClass,
                      final List<GetterDetails> getters)
    {
        Checks.nonNull(packageName, "packageName");
        Checks.nonNull(imports, "imports");
        Checks.nonNull(sourceClass, "sourceClass");
        Checks.nonNull(getters, "getters");

        this.packageName = packageName;
        this.imports = imports;
        this.sourceClass = sourceClass;
        this.getters = getters;
    }

    public String getPackageName() {
        return packageName;
    }

    public Collection<Class<?>> getImports() {
        return imports;
    }

    public Class<?> getSourceClass() {
        return sourceClass;
    }

    public List<GetterDetails> getGetters() {
        return getters;
    }
}
