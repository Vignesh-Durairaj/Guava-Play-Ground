package com.vikhi.test.utils;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.vikhi.exercises.utils.FileParserHelper;

public class FileParserTest {

	@Test
	public void testAllWordsInFile() {
		assertNull(FileParserHelper.getAllWords(null));
	}
}
