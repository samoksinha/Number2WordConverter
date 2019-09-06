package com.sam.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sam.converter.Number2WordsConverter;
import com.sam.exception.Number2WordsException;

public class Number2WordsConverterTest {

	private static Number2WordsConverter number2WordsConverter;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@BeforeClass
	public static void Number2WordsConverter() {
		number2WordsConverter = Number2WordsConverter.getInstance();
	}

	@Before
	public void beforeEachTest() {
		System.out.println("Test Case executions starts.");
	}

	@After
	public void afterEachTest() {
		System.out.println("Test Case executions complets.");
	}

	@Test
	public void test0() {
		String value = number2WordsConverter.convert("0");
		assertNotNull(value);
		assertTrue("Value : 0 : Converted Value : Zero", value.equalsIgnoreCase("Zero"));
	}
	
	@Test
	public void test1() {
		String value = number2WordsConverter.convert("1");
		assertNotNull(value);
		assertTrue("Value : 1 : Converted Value : One", value.equalsIgnoreCase("One"));
	}
	
	@Test
	public void test21() {
		String value = number2WordsConverter.convert("21");
		assertNotNull(value);
		assertTrue("Value : 21 : Converted Value : Twenty One", value.equalsIgnoreCase("Twenty One"));
	}
	
	@Test
	public void test105() {
		String value = number2WordsConverter.convert("105");
		assertNotNull(value);
		assertTrue("Value : 105 : Converted Value : One Hundred And Five", value.equalsIgnoreCase("One Hundred And Five"));
	}
	
	@Test
	public void test56945781() {
		String value = number2WordsConverter.convert("-56945781");
		assertNotNull(value);
		assertTrue("Value : -56945781 : Converted Value : - Fifty Six Million Nine Hundred And Forty Five Thousand Seven Hundred And Eighty One", value.equalsIgnoreCase("- Fifty Six Million Nine Hundred And Forty Five Thousand Seven Hundred And Eighty One"));
	}
	
	@Test
	public void testOutOfRange() {
		
		thrown.expect(Number2WordsException.class);
        thrown.expectMessage(containsString("Enter Number is out of integer range !"));
        thrown.expect(hasProperty("exceptionCode"));
        thrown.expect(hasProperty("exceptionCode", is(103)));
        
		number2WordsConverter.convert("1111111111");
	}
	
	@Test
	public void testStringInput() {
		
		thrown.expect(Number2WordsException.class);
        thrown.expectMessage(containsString("For input string: \"sdsad\""));
        thrown.expect(hasProperty("exceptionCode"));
        thrown.expect(hasProperty("exceptionCode", is(106)));
        
		number2WordsConverter.convert("sdsad");
	}

}
