package com.vikhi.test.utils;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Multimap;
import com.vikhi.exercises.collections.MultiMapExercise;

public class MultiMapTest extends BaseTest{

	private MultiMapExercise multiMapFactory;
	
	@Before
	public void init() {
		multiMapFactory = new MultiMapExercise();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullFile() {
		multiMapFactory.getListOfWordsByCount(null);
		fail("The above line should throw an exception but was not !");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyFileName() {
		multiMapFactory.getListOfWordsByCount("");
		fail("The above line should throw an exception but was not !");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidFile() {
		multiMapFactory.getListOfWordsByCount("test.test");
		fail("The above line should throw an exception but was not !");
	}
	
	@Test
	public void testMultiMapObject() {
		Multimap<Integer, String> wordsByCountMap = multiMapFactory.getListOfWordsByCount("src/resources/twister.txt");
		assertNotNull(wordsByCountMap);
		assertThat(wordsByCountMap.keys(), hasItem(2));
	}
}
