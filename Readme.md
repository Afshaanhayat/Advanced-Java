# Streams
## What are Streams?
Streams, along with `lambdas`, are the most significant change to the Java APIs since Java 2 Collections. The streams make it possible to use two core principles of functional programming in Java i.e. higher order functions and immutability. 

Streams can be thought of as a sequence of unrealized elements (lazy) which allow multiple operations to be chained together and applied only when necessary. 

Streams are implemented as an extension to the existing collection interface. Any collection can be converted to a stream with the help of a `stream()` method. Streams can also be generated on their own using `Stream.iterate` or `Stream.generate` methods.

Once we have a stream, we can apply higher order functions like map, filter, reduce etc to the stream. Please keep in mind that streams are lazy and any application of higher order functions is done only when necessary.

You can realize a stream (which inturn will realize all elements) by using the Stream#collect() method which takes a `Collector`. A stream can be realized into a `List`, `Collection`, `Set`. Most of the reduction operations like `groupBy` can be done using Collectors. You can also peek into a stream using `first()` method. 

## Multiples
To understand the concept of  streams, let us walk through a few simple examples that use common stream operations. The emphasis here is on what to do rather than how to do it (the core concept behind streams).

The method [`Multiples#multiplesof5`](https://github.com/ShaziaManzoor/MCA-5E7-DCE/blob/master/src/main/java/edu/iust/advancejava/streams/Multiples.java#L12) takes an arbitrary stream of integers and returns a stream which contains only  multiples of `5` , passing them through`filter()` method of streams. This is a typical example of `filtering`.

```java

public static Stream<Integer> multiplesOf5(Stream<Integer> values){
        return values.filter(x -> x % 5 == 0);
 }

```
[`Multiples#multiplesofN`](https://github.com/ShaziaManzoor/MCA-5E7-DCE/blob/master/src/main/java/edu/iust/advancejava/streams/Multiples.java#L20) is a  more flexible method, as it takes a stream and a number and returns a stream which contains only those elements that are a multiple of that number.

```java

public static int sumOfMultiplesOfN(Stream<Integer> values, int n){
       return values.filter(x -> x % n == 0).reduce(0, (result, x) -> result + x);
}

```
 [`Multiples#factors`](https://github.com/ShaziaManzoor/MCA-5E7-DCE/blob/master/src/main/java/edu/iust/advancejava/streams/Multiples.java#L24) takes a number and return an IntStream (a steam of integers which has methods to manipulate integers).

```java

public static IntStream factors(int n){
       final int max = n < 5 ? n : n/2;
       return IntStream.range(1, max)
               .filter(x -> n % x == 0)
               .flatMap(x -> IntStream.of(x, n / x))
               .sorted();
}
```

[`Multiples#isPrime`](https://github.com/ShaziaManzoor/MCA-5E7-DCE/blob/master/src/main/java/edu/iust/advancejava/streams/Multiples.java#L32) uses `factors()` method to count which number has only two factors (the definition of a prime number).

```java

public static boolean isPrime(int n) {
       return factors(n).count() == 2;
}
    
```
[`Multiples#PrimeNumbers`](https://github.com/ShaziaManzoor/MCA-5E7-DCE/blob/master/src/main/java/edu/iust/advancejava/streams/Multiples.java#L36) uses `iterate()` method of IntStream to produce an infinite stream of prime numbers. 

```java

public static IntStream primeNumbers(){
       return IntStream.iterate(1, n -> n + 1)
                       .filter(Multiples::isPrime);
}

```