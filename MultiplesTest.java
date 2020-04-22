package edu.iust.advancejava.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import java.util.stream.Collectors;
import static org.junit.Assert.*;
import static edu.iust.advancejava.streams.Multiples.*;

public class MultiplesTest {

    @Test
    public void testMultiplesOf5() {
        assertEquals(
                Stream.of(5, 15).collect(Collectors.toList()),
                multiplesOf5(Stream.of(5, 6, 7, 15)).collect(Collectors.toList()));
    }

    @Test
    public void testMultiplesOfN() {
        assertEquals(
                Stream.of(7, 14).collect(Collectors.toList()),
                multiplesOfN(Stream.of(3, 7, 8, 9, 14, 15, 17), 7).collect(Collectors.toList()));
    }

    @Test
    public void testSumOfMultiplesOfN() {
        assertEquals(9, sumOfMultiplesOfN(Stream.of(1, 2, 3, 4, 5, 6), 3));
    }

    @Test
    public void testFactors() {
        assertEquals(
                IntStream.of(1, 2, 5, 10).boxed().collect(Collectors.toList()),
                factors(10).boxed().collect(Collectors.toList()));
    }

    @Test
    public void testIsPrime() {
        assertFalse(isPrime(20));
        assertTrue(isPrime(19));
        assertTrue(isPrime(13));
        assertTrue(isPrime(37));
        assertFalse(isPrime(99));
    }

    @Test
    public void testPrimeNumbers() {
        assertEquals(IntStream.of(2, 3, 5, 7, 11).boxed().collect(Collectors.toList()),
                primeNumbers().limit(5).boxed().collect(Collectors.toList()));
    }
}