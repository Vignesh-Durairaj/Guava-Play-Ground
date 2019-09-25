package com.vikhi.exercises.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileParserHelper {

	private FileParserHelper() {
		// No constructor needed for the static class
	}
	
	public static List<String> getAllWords (final Path inputFilePath) throws IOException {
		List<String> words = null;
		if (inputFilePath == null) {
			return words;
		}

		return Files
				.lines(inputFilePath)
				.map(line -> line.replaceAll("[,.]", "").toLowerCase().split(" "))
				.flatMap(arr -> Arrays.stream(arr))
				.filter(word -> !"".equals(word))
				.collect(Collectors.toList());
	}
}
