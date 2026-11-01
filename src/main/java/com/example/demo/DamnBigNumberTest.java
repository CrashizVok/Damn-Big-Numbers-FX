package com.example.demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DamnBigNumberTest {

    @Test public void testIncrement() {
        assertEquals("6", new DamnBigNumber(5).increment().getNumStr());
        assertEquals("100", new DamnBigNumber(99).increment().getNumStr());
        assertEquals("1", new DamnBigNumber("0").increment().getNumStr());
        assertEquals("10000000000000000000", new DamnBigNumber("9999999999999999999").increment().getNumStr());
    }

    @Test public void testDecrement() throws Exception {
        assertEquals("4", new DamnBigNumber(5).decrement().getNumStr());
        assertEquals("98", new DamnBigNumber(99).decrement().getNumStr());
        assertEquals("0", new DamnBigNumber(1).decrement().getNumStr());
        assertEquals("9999999999999999999", new DamnBigNumber("10000000000000000000").decrement().getNumStr());
    }

    @Test public void testAdd() {
        assertEquals("8", DamnBigNumbers.add(new DamnBigNumber(5), 3L).getNumStr());
        assertEquals("46", DamnBigNumbers.add(new DamnBigNumber(12), 34L).getNumStr());
        assertEquals("100", DamnBigNumbers.add(new DamnBigNumber(99), 1L).getNumStr());
        assertEquals("1000", DamnBigNumbers.add(new DamnBigNumber(999), 1L).getNumStr());
        assertEquals("6", DamnBigNumbers.add(new DamnBigNumber("002"), new DamnBigNumber("004")).getNumStr());
    }

    @Test public void testSubtract() throws Exception {
        assertEquals("2", DamnBigNumbers.subtract(new DamnBigNumber(5), 3L).getNumStr());
        assertEquals("22", DamnBigNumbers.subtract(new DamnBigNumber(34), new DamnBigNumber(12)).getNumStr());
        assertEquals("0", DamnBigNumbers.subtract(new DamnBigNumber(7), new DamnBigNumber(7)).getNumStr());
        assertEquals("99", DamnBigNumbers.subtract(new DamnBigNumber(100), 1L).getNumStr());
    }

    @Test public void testMultiply() {
        assertEquals("15", DamnBigNumbers.multiply(new DamnBigNumber(5), 3L).getNumStr());
        assertEquals("408", DamnBigNumbers.multiply(new DamnBigNumber(12), new DamnBigNumber(34)).getNumStr());
        assertEquals("998001", DamnBigNumbers.multiply(new DamnBigNumber(999), new DamnBigNumber(999)).getNumStr());
        assertEquals("0", DamnBigNumbers.multiply(new DamnBigNumber(0), new DamnBigNumber(1234)).getNumStr());
        assertEquals("6", DamnBigNumbers.multiply(new DamnBigNumber("02"), new DamnBigNumber("003")).getNumStr());
    }

    @Test public void testDivide() throws Exception {
        assertEquals("2", DamnBigNumbers.divide(new DamnBigNumber(6), 3L).getNumStr());
        assertEquals("12", DamnBigNumbers.divide(new DamnBigNumber(408), new DamnBigNumber(34)).getNumStr());
        assertEquals("33", DamnBigNumbers.divide(new DamnBigNumber(100), 3L).getNumStr());
        assertEquals("0", DamnBigNumbers.divide(new DamnBigNumber(5), new DamnBigNumber(10)).getNumStr());
    }

    @Test public void testEquals() {
        assertTrue(DamnBigNumbers.areEqual(new DamnBigNumber(7), new DamnBigNumber(7)));
        assertTrue(DamnBigNumbers.areEqual(new DamnBigNumber(7), 7L));
        assertFalse(DamnBigNumbers.areEqual(new DamnBigNumber(7), 8L));
        assertFalse(DamnBigNumbers.areEqual(new DamnBigNumber(0), 7L));
        assertTrue(DamnBigNumbers.areEqual(new DamnBigNumber("007"), 7L));
    }

    @Test public void testPrime() {
        assertTrue(new DamnBigNumber(2).isPrime());
        assertTrue(new DamnBigNumber(7).isPrime());
        assertTrue(new DamnBigNumber(97).isPrime());
        assertFalse(new DamnBigNumber(0).isPrime());
        assertFalse(new DamnBigNumber(1).isPrime());
        assertFalse(new DamnBigNumber(4).isPrime());
        assertFalse(new DamnBigNumber(91).isPrime());
    }
}
