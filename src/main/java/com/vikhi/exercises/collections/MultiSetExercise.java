package com.vikhi.exercises.collections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.vikhi.exercises.utils.FileParserHelper;

public class MultiSetExercise {

	public Multiset<String> getWordOccurrences (final File file) {
		if (file == null) {
			throw new IllegalArgumentException("Input file should not be null");
		}
		
		try {
			return ImmutableMultiset.copyOf(FileParserHelper.getAllWords(Paths.get(file.getPath())));
		} catch (IOException e) {
			throw new IllegalArgumentException("IO Exception occurred while parsing the file : " + file.getAbsolutePath());
		}
	}
}
