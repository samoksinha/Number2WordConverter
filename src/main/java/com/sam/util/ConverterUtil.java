package com.sam.util;

import com.sam.exception.Number2WordsException;

public abstract class ConverterUtil {
	
	public final String EMPTY_TOKEN = "";
	public final String WHITESPACE_TOKEN = " ";
	
	public final String ZERO_TOKEN = "Zero";
	public final String NEGATIVE_TOKEN = "-";
	public final String AND_TOKEN = "And";
	public final String HUNDRED_TOKEN = "Hundred";
	public final String THOUSAND_TOKEN = "Thousand";
	public final String MILLION_TOKEN = "Million";

	public final String ZERO_TO_NINTEEN_TOKEN_ARRAY[] = {
		EMPTY_TOKEN, "One", "Two", "Three", "Four", "Five", "Six",
		"Seven", "Eight", "Nine", "Ten", "Eleven","Twelve", 
		"Thirteen", "Fourteen", "Fifteen", "Sixteen",
		"Seventeen", "Eighteen", "Nineteen"
	};
	
	public final String TWENTY_TO_NINETY_TOKEN_ARRAY[] = {
		EMPTY_TOKEN, EMPTY_TOKEN, "Twenty", "Thirty", "Forty", "Fifty",
		"Sixty", "Seventy", "Eighty", "Ninety"
	};
	
	public String convert(int number) {
		
		String value = null;
		if(number == 0) {
			value =  ZERO_TOKEN;
			
		} else {
			value =  EMPTY_TOKEN;
		}
		
		return value;
	}
	
	public String convert(String numberValue) {
		
		int number = 0;
		StringBuilder wordBuilder = null;
		try {
			wordBuilder = new StringBuilder();
			
			if(numberValue.contains(this.NEGATIVE_TOKEN)
					&& numberValue.length() <= 10) {
				
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
			throw new Number2WordsException(103, e.getMessage(), e);
		}
		
		wordBuilder.trimToSize();
		return wordBuilder.toString().trim();
	}
	
}
