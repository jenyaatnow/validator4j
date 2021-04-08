package io.github.jenyaatnow.validator4j.sample.source;

import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.Getter;

import java.util.Date;

@Getter
@Validatable
public class JavaTypesPojo {
    private static String ignored;

    private Boolean booleanField;
    private Byte byteField;
    private Short shortField;
    private Integer integerField;
    private Long longField;
    private Float floatField;
    private Double doubleField;
    private String stringField;
    private Date dateField;
    private SampleEnum enumField;
}
