package ${template.package};

import com.validator4j.core.ErrorsContainer;
import com.validator4j.core.ValidatableReference;
${template.import.validator4j}
${template.import.root}
${template.import.nested}

import java.util.Objects;


public final class V${template.type.root} extends ValidatableObject<${template.type.root}> {

    ${template.fields}

    /**
     * Root object constructor.
     */
    public V${template.type.root}(final ${template.type.root} value) {
        this(ValidatableReference.PATH_ROOT, value, ErrorsContainer.getErrorsContainer());

        Objects.requireNonNull(value, "Validated object must not be null");
    }

    /**
     * Nested object constructor.
     */
    V${template.type.root}(final String path, final ${template.type.root} value, final ErrorsContainer errors) {
        super(path, value, errors);

        ${template.constructor.assignments}
    }

    ${template.getters}
}
