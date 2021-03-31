package com.validator4j.sample.source;

import com.validator4j.core.Validatable;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Validatable
@RequiredArgsConstructor
public class GenerationSourcePojo {

    private final Integer id;

    private final Integer anotherId;

    private final String name;

    private final Double amount;

    private final Boolean readyToGo;

    private final ArrayList<Integer> articleIds;

    public Integer getId() {
        return id;
    }

    public Integer getAnotherId() {
        return anotherId;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public Boolean getReadyToGo() {
        return readyToGo;
    }

    public ArrayList<Integer> getArticleIds() {
        return articleIds;
    }
}
