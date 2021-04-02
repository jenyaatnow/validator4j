package com.validator4j.sample.source;

import com.validator4j.core.Validatable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Validatable
@Getter
@RequiredArgsConstructor
public class GenerationSourcePojo {

    private final Integer id;

    private final Integer anotherId;

    private final String name;

    private final Double amount;

    private final Boolean readyToGo;

    private final ArrayList<Integer> articleIds;
}
