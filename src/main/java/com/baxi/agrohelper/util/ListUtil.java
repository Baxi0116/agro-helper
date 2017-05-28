package com.baxi.agrohelper.util;

import java.util.Arrays;
import java.util.List;

/**
 * Provides list formating utilities for the other classes.
 * 
 * @author Gergely Szabó
 *
 */
public class ListUtil {

	/**
	 * Formats the {@code List} into a {@code String}.
	 * <p>
	 * The list elements follow each other, they are separated by a comma.
	 * 
	 * @param list {@link java.util.List} to be formated
	 * @param <T> type of the elements in the list
	 * @return formated String which contains the elements
	 */
	public static <T> String formatOutput(List<T> list){
		
		String result = "";
		if(list != null){
			for(int i = 0; i < list.size(); i++){
				if(i != list.size() - 1){
					result += list.get(i);
					result += ", ";
				}else{
					result += list.get(i);
				}
			}
		}
		return result;
	}
	
	/**
	 * Converts a comma separated specification to a {@link java.util.List}.
	 * <p>
	 * Splits the input at the commas, then trims each element, then adds them to a {@code List}. 
	 * 
	 * @param input String with the elements.
	 * @return List whit the separated Strings
	 */
	public static List<String> parseInput(String input){
		String[] parts = input.split(",");
		for(int i = 0; i < parts.length; i++){
			parts[i] = parts[i].trim();
		}
		List<String> partsList = Arrays.asList(parts);

		return partsList;
	}
	
}
