package edu.iust.advancejava.streams.lambda;

public interface BetterAggregator<U, T> {
    U op(U result, T item);
}
