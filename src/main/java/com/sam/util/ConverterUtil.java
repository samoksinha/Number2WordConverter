package com.sam.util;

import com.sam.exception.Number2WordsException;

public abstract class ConverterUtil {

	protected final String EMPTY_TOKEN = "";
	protected final String WHITESPACE_TOKEN = " ";

	protected final String ZERO_TOKEN = "Zero";
	protected final String NEGATIVE_TOKEN = "-";
	protected final String AND_TOKEN = "And";
	protected final String HUNDRED_TOKEN = "Hundred";
	protected final String THOUSAND_TOKEN = "Thousand";
	protected final String MILLION_TOKEN = "Million";

	protected final String ZERO_TO_NINTEEN_TOKEN_ARRAY[] = { EMPTY_TOKEN, "One", "Two", "Three", "Four", "Five", "Six",
			"Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
			"Seventeen", "Eighteen", "Nineteen" };

	protected final String TWENTY_TO_NINETY_TOKEN_ARRAY[] = { EMPTY_TOKEN, EMPTY_TOKEN, "Twenty", "Thirty", "Forty",
			"Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

	protected String convert(int number) {

		String value = null;
		if (number == 0) {
			value = ZERO_TOKEN;

		} else {
			value = EMPTY_TOKEN;
		}

		return value;
	}

// Method to convert single digit or two digit number into words
	protected final String convertUpToTwoDigitNumber(int number, String suffix) 
			throws Number2WordsException {

		StringBuilder convertedwordBuilder = null;
		try {
			convertedwordBuilder = new StringBuilder();

			if (number == 0) {
				convertedwordBuilder.append(EMPTY_TOKEN);
			} else if (number > 19 || number < -19) {
				convertedwordBuilder.append(TWENTY_TO_NINETY_TOKEN_ARRAY[number / 10]);
				convertedwordBuilder.append(WHITESPACE_TOKEN);
				convertedwordBuilder.append(ZERO_TO_NINTEEN_TOKEN_ARRAY[number % 10]);
				convertedwordBuilder.append(WHITESPACE_TOKEN);
				convertedwordBuilder.append(suffix);
			} else {
				convertedwordBuilder.append(ZERO_TO_NINTEEN_TOKEN_ARRAY[number]);
				convertedwordBuilder.append(WHITESPACE_TOKEN);
				convertedwordBuilder.append(suffix);
			}

		} catch (Exception e) {
			throw new Number2WordsException(101, e.getMessage(), e);
		}

		return convertedwordBuilder.toString();
	}

	protected final String addAndClause(int number, String value) {
		StringBuilder builder = new StringBuilder();

		if (value != null && value.length() > 0 && number != 0) {
			builder.append(AND_TOKEN);
			builder.append(WHITESPACE_TOKEN);
		} else {
			builder.append(EMPTY_TOKEN);
		}

		return builder.toString();
	}

	public String convert(String numberValue) 
			throws Number2WordsException {

		int number = 0;
		StringBuilder wordBuilder = null;
		try {
			wordBuilder = new StringBuilder();

			if (numberValue.contains(this.NEGATIVE_TOKEN) && numberValue.length() <= 10) {

				number = Integer.parseInt(numberValue);
				number = Math.abs(number);
				wordBuilder.append(this.NEGATIVE_TOKEN);
				wordBuilder.append(this.WHITESPACE_TOKEN);
				wordBuilder.append(this.convert(number));

			} else if (numberValue.length() <= 9) {
				number = Integer.parseInt(numberValue);
				wordBuilder.append(this.convert(number));

			} else {
				throw new Number2WordsException(103, "Enter Number is out of integer range !");
			}
		} catch (Number2WordsException nwe) {
			throw nwe;
		} catch (Exception e) {
			throw new Number2WordsException(106, e.getMessage(), e);
		}

		wordBuilder.trimToSize();
		return wordBuilder.toString().trim();
	}

}
