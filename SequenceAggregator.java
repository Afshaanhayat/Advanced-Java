package edu.iust.advancejava.streams.lambda;

import java.util.ArrayList;
import java.util.Collection;

public class SequenceAggregator {

    public static int sum(Collection<Integer> list){
        int result = 0;
        for(int item : list)
            result = result + item;
        return result;
    }

    public static int product(Collection<Integer> list){
        int result = 1;
        for(int item: list)
            result = result * item;
        return result;
    }

    public static Collection<Integer> removeNegatives(Collection<Integer> list){
        Collection<Integer> result = new ArrayList<>();
        for(Integer item: list)
            if(item >= 0)
                result.add(item);

        return result;
    }

    public static <T> T simpleReduce(T initValue, Aggregator<T> aggregator, Collection<T> list){
        T result = initValue;

        for(T item: list)
            result = aggregator.op(result, item);
        return result;
    }

    public static <U, T> U betterReduce(U initValue, BetterAggregator<U, T> aggregator, Collection<T> list){
        U result = initValue;
        for(T item: list)
            result = aggregator.op(result, item);
        return result;
    }

    public static <U, T> Collection<U> mapUsingReduce(Collection<T> items, Mapper<U, T> mapper){
        return betterReduce(new ArrayList<U>(), (result, item) -> {
            result.add(mapper.map(item));
            return result;
        }, items);
    }

    public static <T> Collection<T> filterUsingReduce(Collection<T> items, Predicate <T> filter){
        return betterReduce(new ArrayList<>(), (result, item) -> {
            if (filter.test(item))
                result.add(item);
            return result;
        }, items);
    }

    public static Collection<Integer> lengths(Collection<String> strings){
        Collection<Integer> lengths = new ArrayList<>();
        for(String string: strings)
            lengths.add(string.length());
        return lengths;
    }

    public static <U, T> Collection<U> map(Collection<T> items, Mapper<U, T> mapper){
        Collection<U> output = new ArrayList<>();
        for(T item: items)
            output.add(mapper.map(item));
        return output;
    }

    public static <U, T> Collection<U> flatMap(Collection<T> items, Mapper<Collection<U>, T> flatMapper){
        Collection<U> output = new ArrayList<>();
        for(T item: items)
            output.addAll(flatMapper.map(item));
        return output;
    }

    public static <T> Collection<T> filter(Collection<T> items, Predicate<T> filter){
        Collection<T> output = new ArrayList<>();
        for(T item: items)
            if (filter.test(item))
                output.add(item);

        return output;
    }

    public static <T> Collection<T> takeWhile(Collection<T> items, Predicate<T> check){
        Collection<T> output = new ArrayList<>();
        for (T item : items)
            if (check.test(item))
                output.add(item);
            else
                break;
        return output;
    }
}
