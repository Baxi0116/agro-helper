package com.baxi.agrohelper.util;

import java.util.Arrays;
import java.util.List;

public class ListUtil {

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
	
	public static List<String> parseInput(String input){
		String[] parts = input.split(",");
		for(int i = 0; i < parts.length; i++){
			parts[i] = parts[i].trim();
		}
		List<String> partsList = Arrays.asList(parts);

		return partsList;
	}
	
}
