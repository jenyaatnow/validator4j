package com.validator4j.sample.source;

import com.validator4j.core.Validatable;
import lombok.RequiredArgsConstructor;

@Validatable
@RequiredArgsConstructor
public class GenerationSourcePojo {

    private final Integer id;

    private final String name;

    private final Double amount;

    private final Boolean readyToGo;

    public Integer getId() {
        return id;
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
}
