package edu.iust.advancejava.streams.lambda;

public interface Generator<T> {
    T nextValue(T seed);
}
