package edu.iust.advancejava.streams.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
import static edu.iust.advancejava.streams.lambda.SequenceAggregator.*;

public class SequenceAggregatorTest {

    @Test
    public void testSum() throws Exception {
        assertEquals(15, sum(Arrays.asList(1,2,3,4,5)));
        assertEquals(0, sum(Collections.emptyList()));
    }

    @Test
    public void testProduct() throws Exception {
        assertEquals(120, product(Arrays.asList(1,2,3,4,5)));
        assertEquals(1, product(Arrays.asList(new Integer[]{})));
    }

    @Test
    public void testRemoveNegatives() throws Exception {
        assertArrayEquals(new Integer[]{2,3,5}, removeNegatives(Arrays.asList(-1,2,3,-4,5)).toArray());
        assertArrayEquals(new Integer[]{}, removeNegatives(Arrays.asList(new Integer[]{})).toArray());
    }

    @Test
    public void testSimpleReduce() throws Exception {
        assertEquals(new Integer(15), simpleReduce(0, (result, item) -> result + item, Arrays.asList(1, 2, 3, 4, 5)));
        assertEquals(new Integer(120), simpleReduce(1, (result, item) -> result * item, Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    public void testBetterReduce() throws Exception {
        assertEquals(new Integer(15), betterReduce(0, (result, item) -> result + item, Arrays.asList(1, 2, 3, 4, 5)));
        assertEquals(new Integer(120), betterReduce(1, (result, item) -> result * item, Arrays.asList(1, 2, 3, 4, 5)));

        assertArrayEquals(new Integer[]{1, 2, 3, 5},
                betterReduce(new ArrayList<Integer>(), (result, item) -> {
                    if (item >= 0)
                        result.add(item);
                    return result;
                }, Arrays.asList(1, 2, -5, -6, 3, -4, 5)).toArray());

        assertArrayEquals(new Integer[]{3, 4, 6},
                betterReduce(new ArrayList<Integer>(), (result, item) -> {
                    result.add(item.length());
                    return result;
                }, Arrays.asList("cat", "dogs", "donkey")).toArray());

    }




    @Test
    public void testMapUsingReduce() throws Exception {
        assertArrayEquals(new Integer[]{8, 7, 9},
                mapUsingReduce(Arrays.asList("Srinagar", "Pulwama", "Baramulla"), String::length).toArray());

        assertArrayEquals(new String[]{"SRINAGAR", "PULWAMA", "BARAMULLA"},
                mapUsingReduce(Arrays.asList("Srinagar", "Pulwama", "Baramulla"), String::toUpperCase).toArray());
    }

    @Test
    public void testFilterUsingReduce() throws Exception {
        assertArrayEquals(new Integer[]{8, 9, 10},
                filterUsingReduce(Arrays.asList(2, 3, 5, 8, 9, 10), i -> i > 5).toArray());
    }

    @Test
    public void testLengths() throws Exception {
        assertArrayEquals(new Integer[]{8, 7, 9},
                lengths(Arrays.asList("Srinagar", "Pulwama", "Baramulla")).toArray());
    }

    @Test
    public void testMap() throws Exception {
        assertArrayEquals(new Integer[]{8, 7, 9},
                map(Arrays.asList("Srinagar", "Pulwama", "Baramulla"), String::length).toArray());

        assertArrayEquals(new String[]{"SRINAGAR", "PULWAMA", "BARAMULLA"},
                map(Arrays.asList("Srinagar", "Pulwama", "Baramulla"), String::toUpperCase).toArray());
    }

    @Test
    public void testFlatMap() throws Exception {
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6},
                flatMap(Arrays.asList(new Integer[]{1, 2}, new Integer[]{3, 4}, new Integer[]{5, 6}), Arrays::asList).toArray());

        Mapper<Collection<Integer>, Integer> fourMultiples = x -> SequenceGenerator.range(x, 5 * x, x);
        assertArrayEquals(new Integer[]{2, 4, 6, 8, 3, 6, 9, 12},
                flatMap(Arrays.asList(2, 3), fourMultiples).toArray());
    }

    @Test
    public void testFilter() throws Exception {
        assertArrayEquals(new String[]{"Afshan"},
                filter(Arrays.asList("Basit", "Nasir", "Iqra", "Afshan", "Zakir"),
                        s -> s.length() > 5).toArray());
    }

    @Test
    public void testTakeWhile() throws Exception {
        assertArrayEquals(new Integer[]{1, 2, 5, 6},
                takeWhile(Arrays.asList(1, 2, 5, 6, -4, 6, 8), i -> i > 0).toArray());
    }
}