package edu.iust.advancejava.streams.lambda;

import java.util.ArrayList;
import java.util.Collection;


public class SequenceGenerator {

    public static Collection<Integer> range(int min, int max, int step) {
        Collection<Integer> integers = new ArrayList<>();
        for (int i = min; i < max; i += step)
            integers.add(i);
        return integers;
    }

    public static Collection<Integer> rangeWithStepLogic(int min, int max, StepLogic logic) {
        Collection<Integer> integers = new ArrayList<>();
        for (int i = min; i < max; i = logic.increment(i))
            integers.add(i);
        return integers;
    }

    public static <T> Collection<T> genericSeriesWithLogic(T seed, Generator<T> generator) {
        Collection<T> series = new ArrayList<>();
        series.add(seed);
        do {
            T newValue = generator.nextValue(seed);
            if (newValue == null)
                return series;
            series.add(newValue);
            seed = newValue;
        } while (true);
    }

}









