package com.vikhi.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import com.vikhi.exercises.utils.FileParserHelper;

public class FileParserTest {

	@Test
	public void testAllWordsInFile() {
		List<String> words = FileParserHelper.getAllWords(Paths.get("twister.txt"));
		assertNotNull(words);
		assertEquals(75, words.size());
		System.out.println(words);
	}
}
