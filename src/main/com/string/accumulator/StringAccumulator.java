package com.string.accumulator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAccumulator {
	final String NUMBER_SEPARATOR_DELIMITERS = ",|\n";  //to handle multiple delimiter ',' new line(]n)
	final String SET_A_DELIMITER ="//";  //starting characters to set a new delimeter
	final int MAX_LIMIT  = 1000; // limit after that number should be ignored (inclusive)
	 
	
	/**
	 * 
	 * @param numbers
	 * @return sum of numbers
	 */
	public int add(String numbers){
		String numbersWithoutDelimiters = null; //to store valid numbers after removing delimiter if delimiter is changed
		String delimiter = null; //to store actual delimiter provided in numbers
		
		if(numbers.startsWith(SET_A_DELIMITER)){
			int indexOfDelimiter =   numbers.indexOf("//") + 2;
			 delimiter = numbers.substring(indexOfDelimiter, numbers.indexOf("\n"));
			 numbersWithoutDelimiters = numbers.substring(numbers.indexOf("\n") + 1);
			 return add(numbersWithoutDelimiters, delimiter);
		}
		//if no any delimiter change is set then pass actual input numbers and possible delimiters to overridden add()
		return add(numbers, NUMBER_SEPARATOR_DELIMITERS);
	}
	

	/**
	 * 
	 * @param numbers
	 * @param delimiter
	 * @return sum of numbers
	 */
	public int add(String numbers, String delimiters) {
		int sum = 0;
		int number_int = 0;
		
		String[] numbers_arr = numbers.split(delimiters);
		List<Integer> list_negative_numbers = new ArrayList<Integer>(); // to store possible -ve numbers

		for (String number : numbers_arr) {
			if (!number.trim().isEmpty()) {
				number_int = Integer.parseInt(number.trim());
				if (number_int < 0) {
					list_negative_numbers.add(number_int);
				} else if (number_int <= MAX_LIMIT) {
					sum += number_int;
				}
			}
		}

		if (list_negative_numbers.size() > 0) {
			throw new RuntimeException("negatives not allowed: " + list_negative_numbers.toString());
		}

		return sum;
	}

	

	
	public static void main(String[] args) {
		StringAccumulator strAcc = new StringAccumulator();
		System.out.println(strAcc.add(""));
		System.out.println(strAcc.add("1"));
		System.out.println(strAcc.add("1,2"));
		System.out.println(strAcc.add("1,3,5,7,9,11"));
		System.out.println(strAcc.add("1,2\n3"));
		System.out.println(strAcc.add("//;\n1;3;5"));
		System.out.println(strAcc.add("1,1000,1001,3,1234,100000"));
		System.out.println(strAcc.add("//-----\n1-----2-----3"));
		System.out.println(strAcc.add("//;|%\n1;2%3"));
		//System.out.println(strAcc.add("1,-2,3,-4,5,-6"));
		//System.out.println(strAcc.add("1,G")); //garbage input
	}

}
