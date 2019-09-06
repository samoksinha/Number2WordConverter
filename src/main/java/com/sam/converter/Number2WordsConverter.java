package com.sam.converter;

import com.sam.exception.Number2WordsException;
import com.sam.util.ConverterUtil;

public class Number2WordsConverter extends ConverterUtil {

	private static volatile Number2WordsConverter instance;
	private static Object MUTEX = new Object();

// Making the constructor private so that object can't be created from outside of the class
	private Number2WordsConverter() {
	}

// Return Singleton instance of the class
	public static Number2WordsConverter getInstance() {
		if (instance == null) {
			synchronized (MUTEX) {
				if (instance == null)
					instance = new Number2WordsConverter();
			}
		}
		
		return instance;
	}

// Method to convert a given number (max 9-digits) into words
	@Override
	protected String convert(int number) 
			throws Number2WordsException {
		
		StringBuilder converBuilder = null;
		StringBuilder tokenBuilder = null;
		try {
			converBuilder = new StringBuilder();
			tokenBuilder = new StringBuilder();
			
			converBuilder.append(super.convert(number));
			converBuilder.append(this.addAndClause(number, converBuilder.toString()));
			
			tokenBuilder.append(HUNDRED_TOKEN);
			tokenBuilder.append(WHITESPACE_TOKEN);
			converBuilder.append(this.convertUpToTwoDigitNumber(
					((number / 100000000)), tokenBuilder.toString()));
			number = (number % 100000000);
			converBuilder.append(this.addAndClause(number, converBuilder.toString()));
			
			tokenBuilder.setLength(0);
			tokenBuilder.append(MILLION_TOKEN);
			tokenBuilder.append(WHITESPACE_TOKEN);
			converBuilder.append(this.convertUpToTwoDigitNumber(
					((number / 1000000)), tokenBuilder.toString()));
			number = (number % 1000000);
			
			tokenBuilder.setLength(0);
			tokenBuilder.append(HUNDRED_TOKEN);
			tokenBuilder.append(WHITESPACE_TOKEN);
			converBuilder.append(this.convertUpToTwoDigitNumber(
					((number / 100000)), tokenBuilder.toString()));
			number = (number % 100000);
			converBuilder.append(this.addAndClause(number, converBuilder.toString()));
			
			tokenBuilder.setLength(0);
			tokenBuilder.append(THOUSAND_TOKEN);
			tokenBuilder.append(WHITESPACE_TOKEN);
			converBuilder.append(this.convertUpToTwoDigitNumber(
					((number / 1000)), tokenBuilder.toString()));
			number = (number % 1000);
			
			tokenBuilder.setLength(0);
			tokenBuilder.append(HUNDRED_TOKEN);
			tokenBuilder.append(WHITESPACE_TOKEN);
			converBuilder.append(this.convertUpToTwoDigitNumber(
					((number / 100)), tokenBuilder.toString()));
			number = (number % 100);
			converBuilder.append(this.addAndClause(number, converBuilder.toString()));

			converBuilder.append(this.convertUpToTwoDigitNumber(
					(number % 100), WHITESPACE_TOKEN));
			
		} catch (Number2WordsException nwe) {
			throw nwe;
		} catch (Exception e) {
			throw new Number2WordsException(102, e.getMessage(), e);
		}

		converBuilder.trimToSize();
		return converBuilder.toString().trim();
	}

}
