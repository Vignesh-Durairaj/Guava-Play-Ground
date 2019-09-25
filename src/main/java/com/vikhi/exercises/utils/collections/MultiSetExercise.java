package com.vikhi.exercises.utils.collections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.vikhi.exercises.utils.FileParserHelper;

public class MultiSetExercise {

	public Multiset<String> getWordOccurrences (final File file) throws UnexpectedException {
		if (file == null) {
			throw new IllegalArgumentException("Input file should not be null");
		}
		
		try {
			return ImmutableMultiset.copyOf(FileParserHelper.getAllWords(Paths.get(file.getAbsolutePath())));
		} catch (IOException e) {
			throw new UnexpectedException("IO Exception occurred while parsing the file : " + file.getAbsolutePath());
		}
	}
}
