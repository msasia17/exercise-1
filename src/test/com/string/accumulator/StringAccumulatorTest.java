package com.string.accumulator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringAccumulatorTest {
	StringAccumulator strAcc;

	@Before
	public void createAnStringAccumulator() {
		strAcc = new StringAccumulator();
	}

	@Test
	public final void testEmptyInputStringShouldReturn0() {
		Assert.assertEquals(0, strAcc.add(""));
	}

	@Test
	public final void testOneNumberStringShouldReturnSameValue() {
		Assert.assertEquals(1, strAcc.add("1"));
	}

	@Test
	public final void testTwoNumbersStringShouldReturnSum() {
		Assert.assertEquals(3, strAcc.add("1,2"));
	}

	@Test
	public final void testMultipleNumbersStringShouldReturnSum() {
		Assert.assertEquals(36, strAcc.add("1,3,5,7,9,11")); // "1+3+5+7+9+11=36
	}

	@Test
	public final void testNumbersWithNewLineShouldReturnSum() {
		Assert.assertEquals(6, strAcc.add("1,2\n3"));
	}

	@Test
	public final void testNumbersMoreThan1000ShouldIgnoreTheseNumbersAndRetunSum() { // TODO :Too big name ?
		Assert.assertEquals(1004, strAcc.add("1,1000,1001,3,1234,100000")); // 1+1000+3 only
	}

	@Test
	public final void testNumbersWithLongLengthDelimitersShouldReturnSum() { 
		Assert.assertEquals(6, strAcc.add("//---\n1---2---3")); // 
	}
	
	@Test
	public final void testNumbersWithMultipleDelimitersShouldReturnSum() { 
		Assert.assertEquals(6, strAcc.add("//-|%\n1-2%3")); // 
	}
	
	
	
	
	
	// Since NumberFormatException is not handled (as described in given problem statement)
	// so passing invalid number will cause exception
	@Test(expected = NumberFormatException.class)
	public final void testInvalidNumberStringShouldThrowNumberFormatException() {
		strAcc.add("1,G");
	}

	// To test #5 in problem statement (negative number display with exception)
	@Test
	public final void testNegativeNumberStringShouldThrowExceptionWithNumberDisplay() {
		RuntimeException exception = null;
		try {
			strAcc.add("1,-2,3,-4,5,-6");
		} catch (RuntimeException e) {
			exception = e;
		}
		Assert.assertNotNull("Exception was not thrown", exception);
		Assert.assertEquals("negatives not allowed: [-2, -4, -6]", exception.getMessage());
	}
	
}
