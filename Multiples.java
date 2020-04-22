package edu.iust.advancejava.streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * These are small utility methods related to primary school maths.
 * If you want to extend this you can write function to calculate LCM and HCF.
 */
public class Multiples {
    /**
     * A method to filter elements that are multiple of 5.
     * @param values input stream
     * @return stream with only multiples of 5.
     */
    public static Stream<Integer> multiplesOf5(Stream<Integer> values){
        return values.filter(x -> x % 5 == 0);
    }

    /**
     * A more generic filter function compared to the @{link #multiplesOf5}
     * @param values input stream.
     * @param n multiplier
     * @return stream with only multiples of <code>n</code>
     */
    public static Stream<Integer> multiplesOfN(Stream<Integer> values, int n){
        return values.filter(x -> x % n == 0);
    }

    /**
     * A method to calculate sum of elements of a given stream which are multiplies of n.
     * @param values input stream.
     * @param n multiplier
     * @return calculated sum.
     */
    public static int sumOfMultiplesOfN(Stream<Integer> values, int n){
        return values.filter(x -> x % n == 0).reduce(0, (result, x) -> result + x);
    }

    /**
     * This method finds the factors of a given number.
     * @param n input.
     * @return stream of factors.
     */
    public static IntStream factors(int n){
        final int max = n < 5 ? n : n/2;
        return IntStream.range(1, max)
                .filter(x -> n % x == 0)
                .flatMap(x -> IntStream.of(x, n / x))
                .sorted();
    }

    /**
     * Checks if a number is prime.
     * @param n input
     * @return true if the number is prime otherwise false.
     */
    public static boolean isPrime(int n) {
        return factors(n).count() == 2;
    }

    /**
     * Generates an infinite stream of prime numbers.
     *
     * @return stream of prime numbers.
     */
    public static IntStream primeNumbers(){
        return IntStream.iterate(1, n -> n + 1)
                .filter(Multiples::isPrime);
    }

}
