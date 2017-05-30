package com.baxi.agrohelper.util;

/*-
 * #%L
 * agro-helper
 * %%
 * Copyright (C) 2017 University of Debrecen, Faculty of Informatics
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * #L%
 */

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
