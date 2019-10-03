package com.vikhi.exercises.collections;

import java.io.IOException;
import java.nio.file.Paths;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.vikhi.exercises.utils.FileParserHelper;

public class MultiMapExercise {
	
	public Multimap<Integer, String> getListOfWordsByCount(final String filePath) {
		if (filePath == null || filePath.trim().equals("")) {
			throw new IllegalArgumentException("File name should not be null or empty !");
		}
		
		try {
			Multimap<Integer, String> guavaMultiMap = MultimapBuilder.hashKeys().arrayListValues().build();
			ImmutableMultiset.copyOf(FileParserHelper.getAllWords(Paths.get(filePath)))
				.entrySet()
				.stream()
				.forEach(entry -> guavaMultiMap.put(entry.getCount(), entry.getElement()));
			 
			 return guavaMultiMap;
		} catch (IOException e) {
			throw new IllegalArgumentException("Error in finding or parsing the input file");
		}
	}

}
