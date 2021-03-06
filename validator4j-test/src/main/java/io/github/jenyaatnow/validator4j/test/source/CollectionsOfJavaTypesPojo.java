package io.github.jenyaatnow.validator4j.test.source;

import io.github.jenyaatnow.validator4j.core.V4jIgnore;
import io.github.jenyaatnow.validator4j.core.Validatable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

@Getter
@Validatable
public class CollectionsOfJavaTypesPojo {
    @V4jIgnore
    private Collection<String> collectionIgnored;
    private static Collection<String> staticIgnored;

    private Collection<Boolean> booleans;
    private List<Byte> bytes;
    private ArrayList<Short> shorts;
    private LinkedList<Integer> integers;
    private Set<Long> longs;
    private HashSet<Float> floats;
    private LinkedHashSet<Double> doubles;
    private Queue<String> strings;
    private Stack<Date> dates;
    private Collection<SampleEnum> enums;
}
