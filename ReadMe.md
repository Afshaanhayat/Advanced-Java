# Lambda

To understand how streams work we have to review a few concepts learned in the previous class.
	
The first one is the concept of **lambda**. Java 8 onwards, we use **lambdas** to pass functionality to a function which was something we achieved earlier using interfaces implemented via **anonymous classes**.

To help you understand the transition, let us take an example of a simple Sequence Generator. The purpose of the sequence generator is to provide us with a way to create simple range. 

## Example : Sequence Generator

The first implementation is a [`SequenceGenerator #range`](https://github.com/ShaziaManzoor/MCA-5E7-DCE/blob/master/src/main/java/edu/iust/advancejava/streams/lambda/SequenceGenerator.java#L14) which takes a 'min' and a 'max' values and generates a sequence with each given 'step'.Take a look at the signature of the function and appreciate the limitation of the `step` parameter. Being an integer, it limits the functionality of this function. 


```java
public static Collection<Integer> range(int min, int max, int step) {
        Collection<Integer> integers = new ArrayList<>();
        for (int i = min; i < max; i += step)
            integers.add(i);
        return integers;
    }
```

This function can be used to create a range with constant skip counting using `step` but it can't be used for generating sequences with variable skip counting e.g. 1, 2, 4, 8, 16, ... 

The second implementation is [`SequenceGenerator #rangeWithStepLogic`](https://github.com/ShaziaManzoor/MCA-5E7-DCE/blob/master/src/main/java/edu/iust/advancejava/streams/lambda/SequenceGenerator.java#L22) . This method is similar to the `range` method but takes an interface `StepLogic` instead of an `integer`. 

```java
public static Collection<Integer> rangeWithStepLogic(int min, int max, StepLogic logic) {
        Collection<Integer> integers = new ArrayList<>();
        for (int i = min; i < max; i = logic.increment(i))
            integers.add(i);
        return integers;
    }
```

This is more flexible compared to the previous method because we can generate ranges with custom increment logic. e.g. to generate a range like 1, 2, 4, 8, 16, ... we can do something like this.

```java
public void usingRangeWithStepLogic() throws Exception {
        assertArrayEquals(
                rangeWithStepLogic(1, 20, new StepLogic() {
                    @Override
                    public int increment(int i) { return i + i; }
                }).toArray(),
                new Integer[]{1, 2, 4, 8, 16}
        );
    }
```

Although this implementation is more flexible we are still limited by the `increment` method which only allows us to change create a range within limits. 

Our third method [`SequenceGenerator #generateSeriesWithLogic`](https://github.com/ShaziaManzoor/MCA-5E7-DCE/blob/master/src/main/java/edu/iust/advancejava/streams/lambda/SequenceGenerator.java#L29) overcomes this limitation by checking the return value from our generator `nextValue` and exits the loop when the returned value is `null`.  This allows us to create a generator with a custom range in addition to a custom step logic.

```java

public void usingGenericSeriesWithLogic() throws Exception {
    assertArrayEquals(
                genericSeriesWithLogic(1, seed -> seed + seed > 20 ? null : seed + seed).toArray(),
                new Integer[]{1, 2, 4, 8, 16});
}
```


## Using Lambda

In this example, we are creating methods that can take functionality as anonymous classes and then we use the lambda syntactic sugar to show how instead of an anonymous class you can pass a lambda function as shown in the example below.

```java
public void usingRangeWithStepLogic() throws Exception {
        assertArrayEquals(
                rangeWithStepLogic(1, 20, new StepLogic() {
                    @Override
                    public int increment(int i) { return i + i; }
                }).toArray(),
                new Integer[]{1, 2, 4, 8, 16}
        );
        assertArrayEquals(
                rangeWithStepLogic(2, 100, (i) -> i * i).toArray(),
                new Integer[]{2, 4, 16}
        );
    }
```
