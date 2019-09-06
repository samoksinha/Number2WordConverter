package com.sam.bootstrap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.sam.converter.Number2WordsConverter;
import com.sam.exception.Number2WordsException;

public class Number2WordsConverterBootstrap {

	public static void main(String[] args) {

		Number2WordsConverter number2WordsConverter = null;
		String value = null;
		String convertedValue = null;
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
			number2WordsConverter = Number2WordsConverter.getInstance();
			
			System.out.println("Please enter the number to convert in words : ");
			value = bufferedReader.readLine();
			
			convertedValue = number2WordsConverter.convert(value);
			
			System.out.println("Number to Convert : " +value+ " : Word Value : " +convertedValue);
			
		} catch (Number2WordsException nwe) {
			System.err.println("Custom Exception Occured : Error Message : " +nwe.getMessage());
			System.err.println("Custom Exception Occured : Error Stacktrace : ");
			nwe.printStackTrace();
		} catch (Exception e) {
			System.err.println("Generic Exception Occured : Error Message : " +e.getMessage());
			System.err.println("Generic Exception Occured : Error Stacktrace : ");
			e.printStackTrace();
		}
	}

}
