package com.vikhi.test.utils;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.vikhi.exercises.collections.MultiMapExercise;

@DisplayName("While making the multi map as a map for List for a key")
public class MultiMapTest extends BaseTest{

	private MultiMapExercise multiMapFactory;
	
	@BeforeEach
	public void init() {
		multiMapFactory = new MultiMapExercise();
	}
	
	@DisplayName("should throw an exception while trying to get multi map from a NULL file")
	@Test
	public void testNullFile() {
		assertThrows(IllegalArgumentException.class, () -> multiMapFactory.getListOfWordsByCount(null));
	}
	
	@DisplayName("should throw an exception while trying to get multi map from a empty file name")
	@Test
	public void testEmptyFileName() {
		assertThrows(IllegalArgumentException.class, () -> multiMapFactory.getListOfWordsByCount(""));
	}
	
	@DisplayName("should throw an exception while trying to get multi map from an invalid file")
	@Test
	public void testInvalidFile() {
		assertThrows(IllegalArgumentException.class, () -> multiMapFactory.getListOfWordsByCount("test.test"));
	}
	
	@DisplayName("should create a multimap from a valid input file, consisting list of words and their occurrences as key")
	@Test
	public void testMultiMapObject() {
		Multimap<Integer, String> wordsByCountMap = multiMapFactory.getListOfWordsByCount("src/resources/twister.txt");
		assertNotNull(wordsByCountMap);
		assertThat(wordsByCountMap.keys(), hasItem(2));
		wordsByCountMap
			.entries()
			.stream()
			.forEach(entry -> {
				LOGGER.debug(entry.getKey() + " --> " + entry.getValue());
				assertNotSame(List.class, entry.getValue().getClass());
			});
		
		assertTrue(wordsByCountMap.get(0) instanceof List);
		assertThat(wordsByCountMap.size(), is(28));
		
		Multimap<Integer, String> anotherMultiMap = ImmutableMultimap.copyOf(wordsByCountMap);
		anotherMultiMap
			.entries()
			.stream()
			.forEach(entry -> {
				wordsByCountMap.get(entry.getKey())
					.stream()
					.forEach(entryValue -> {
						assertNotNull(entryValue);
						LOGGER.debug("Entry Value : '" + entryValue + "' of count : " + entry.getKey());
					});
			});
		
		int count = wordsByCountMap.get(2).size();
		wordsByCountMap.put(2, "anotherWord");
		assertEquals(count + 1, wordsByCountMap.get(2).size());
		assertThat(wordsByCountMap.get(2).toString(), containsString("anotherWord"));
		
		try {
			assertThat(anotherMultiMap.get(2).size(), is(count));
			anotherMultiMap.put(2, "anotherString");
			fail("The above operation should throw an exception");
		} catch (UnsupportedOperationException e) {
			LOGGER.warn("This operation is not supported");
		}
	}
}
