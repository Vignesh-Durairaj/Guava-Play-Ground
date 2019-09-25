package com.vikhi.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Multiset;
import com.vikhi.exercises.utils.collections.MultiSetExercise;

public class MultiSetTest {

	private MultiSetExercise multiSetExercise;
	
	@Before
	public void init() {
		multiSetExercise = new MultiSetExercise();
	}
	
	@Test
	public void testValidFile() {
		File inputFile = new File("twister.txt");
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
			.forEach(entry -> System.out.println(entry.getElement() + ":" + entry.getCount()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullFile() {
		multiSetExercise.getWordOccurrences(null);
		fail("The above line should throw an exception but was not !");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidFile() {
		multiSetExercise.getWordOccurrences(new File("test.test"));
		fail("The above line should throw an exception but was not !");
	}
}
