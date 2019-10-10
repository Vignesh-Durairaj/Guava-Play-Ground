package com.vikhi.test.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Multiset;
import com.vikhi.exercises.collections.MultiSetExercise;

@DisplayName("While creating a multi set object from an input file")
public class MultiSetTest {

	private static final Logger LOGGER = LogManager.getLogger();
	
	private MultiSetExercise multiSetExercise;
	
	@BeforeEach
	public void init() {
		multiSetExercise = new MultiSetExercise();
	}
	
	@DisplayName("should create a multilist from a valid input file")
	@Test
	public void testValidFile() {
		File inputFile = new File("src/resources/twister.txt");
		Multiset<String> wordOccurrence = multiSetExercise.getWordOccurrences(inputFile);
		
		assertNotNull(wordOccurrence);
		assertNotNull(wordOccurrence.elementSet());
		assertTrue(wordOccurrence.elementSet().size() > 0);
		assertEquals(6, wordOccurrence.count("butter"));
		assertEquals(7, wordOccurrence.count("her"));
		
		wordOccurrence
			.entrySet()
			.stream()
			.sorted(Comparator.comparing(Multiset.Entry::getCount))
			.forEach(entry -> LOGGER.debug(entry.getElement() + ":" + entry.getCount()));
	}
	
	@DisplayName("should throw an exception while creating a multiset from a NULL input as file name")
	@Test
	public void testNullFile() {
		assertThrows(IllegalArgumentException.class, () -> multiSetExercise.getWordOccurrences(null));
	}
	
	@DisplayName("should throw an exception while creating a multiset from an invalid file")
	@Test
	public void testInvalidFile() {
		assertThrows(IllegalArgumentException.class, () -> multiSetExercise.getWordOccurrences(new File("test.test")))
		;
	}
}
