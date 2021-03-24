package ${template.package};

import com.validator4j.core.ErrorsContainer;
import com.validator4j.core.ValidatableCollection;
${template.import.root}

import java.util.Collection;


public final class V${template.type.root}Collection extends ValidatableCollection<${template.type.root}, V${template.type.root}> {

    public V${template.type.root}Collection(final String path, final Collection<${template.type.root}> value, final ErrorsContainer errors) {
        super(
            path,
            value,
            (p, v) -> new V${template.type.root}(p, v, errors),
            errors
        );
    }
}
