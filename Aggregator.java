package edu.iust.advancejava.streams.lambda;

public interface Aggregator<T> {
    T op(T result, T value);
}
