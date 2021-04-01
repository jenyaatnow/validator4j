package com.validator4j.sample.generated;

import com.validator4j.core.ErrorsContainer;
import com.validator4j.core.ValidatableCollection;
import com.validator4j.core.ValidatableInteger;
import com.validator4j.core.ValidatableObject;
import com.validator4j.core.ValidatableReference;
import com.validator4j.sample.source.NestedPojo;
import com.validator4j.sample.source.TestPojo;
import com.validator4j.util.Checks;

@SuppressWarnings("checkstyle:LineLength")
public final class VTestPojo extends ValidatableObject<TestPojo> {

    private final ValidatableInteger id;

    private final VNestedPojo nested;

    private final ValidatableCollection<Integer, ValidatableInteger> articleIds;

    private final VNestedPojoCollection pojos;

    /**
     * Root object constructor.
     */
    public VTestPojo(final TestPojo value) {
        this(ValidatableReference.PATH_ROOT, value, ErrorsContainer.getErrorsContainer());

        Checks.nonNull(value, "value");
    }

    /**
     * Nested object constructor.
     */
    VTestPojo(final String path, final TestPojo value, final ErrorsContainer errors) {
        super(path, value, errors);

        this.id = new ValidatableInteger(appendPath("id"), safeGet(value, TestPojo::getId), errors);
        this.nested = new VNestedPojo(appendPath("nested"), safeGet(value, TestPojo::getNested), errors);
        this.articleIds = new ValidatableCollection<>(appendPath("articleIds"), safeGet(value, TestPojo::getArticleIds), errors);
        this.pojos = new VNestedPojoCollection(appendPath("pojos"), safeGet(value, TestPojo::getPojos), errors);
    }

    public ValidatableInteger getId() {
        return id;
    }

    public VNestedPojo getNested() {
        return nested;
    }

    public ValidatableCollection<Integer, ValidatableInteger> getArticleIds() {
        return articleIds;
    }

    public ValidatableCollection<NestedPojo, VNestedPojo> getPojos() {
        return pojos;
    }
}
