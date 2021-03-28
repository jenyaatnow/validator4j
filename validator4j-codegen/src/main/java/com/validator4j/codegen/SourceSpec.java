package com.validator4j.codegen;

import com.validator4j.util.Checks;

import javax.lang.model.element.TypeElement;
import java.util.Collection;
import java.util.List;

public class SourceSpec {

    private final String packageName;
    private final Collection<TypeElement> imports;
    private final TypeElement sourceClass;
    private final List<GetterDetails> getters;

    public SourceSpec(final String packageName,
                      final Collection<TypeElement> imports,
                      final TypeElement sourceType,
                      final List<GetterDetails> getters)
    {
        Checks.nonNull(packageName, "packageName");
        Checks.nonNull(imports, "imports");
        Checks.nonNull(sourceType, "sourceType");
        Checks.nonNull(getters, "getters");

        this.packageName = packageName;
        this.imports = imports;
        this.sourceClass = sourceType;
        this.getters = getters;
    }

    public String getPackageName() {
        return packageName;
    }

    public Collection<TypeElement> getImports() {
        return imports;
    }

    public TypeElement getSourceType() {
        return sourceClass;
    }

    public List<GetterDetails> getGetters() {
        return getters;
    }
}
