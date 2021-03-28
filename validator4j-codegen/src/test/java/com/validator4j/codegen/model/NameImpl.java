package com.validator4j.codegen.model;

import com.validator4j.util.Checks;

import javax.lang.model.element.Name;

public class NameImpl implements Name {

    private final String name;

    NameImpl(final String name) {
        Checks.nonNull(name, "name");
        this.name = name;
    }

    @Override
    public boolean contentEquals(CharSequence cs) {
        return name.contentEquals(cs);
    }

    @Override
    public int length() {
        return name.length();
    }

    @Override
    public char charAt(int index) {
        return name.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return name.subSequence(start, end);
    }

    @Override
    public String toString() {
        return name;
    }
}
