package com.vikhi.test.utils;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

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
	public void testInvalidFile() {
		multiMapFactory.getListOfWordsByCount("test.test");
		fail("The above line should throw an exception but was not !");
	}
}
