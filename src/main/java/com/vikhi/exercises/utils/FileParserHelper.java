package com.vikhi.exercises.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileParserHelper {

	private static final Logger LOGGER = LogManager.getLogger(FileParserHelper.class);
	
	private FileParserHelper() {
		// No constructor needed for the static class
	}
	
	public static List<String> getAllWords (final Path inputFilePath) throws IOException {
		List<String> words = null;
		if (inputFilePath == null) {
			return words;
		}

		try (Stream<String> fileStream = Files.lines(inputFilePath)) {
			words = fileStream
					.map(line -> line.replaceAll("[,.]", "").toLowerCase().split(" "))
					.flatMap(Arrays::stream)
					.filter(word -> !"".equals(word))
					.collect(Collectors.toList());
		} catch (IOException e) {
			LOGGER.error("Exception in parsing the input file", e);
			throw e;
		}
				
		return words;
	}
}
