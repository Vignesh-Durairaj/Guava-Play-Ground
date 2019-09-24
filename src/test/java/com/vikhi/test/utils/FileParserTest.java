package com.vikhi.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import com.vikhi.exercises.utils.FileParserHelper;

public class FileParserTest {

	@Test
	public void testAllWordsInFile() {
		List<String> words = null;
		try {
			words = FileParserHelper.getAllWords(Paths.get("twister.txt"));
		} catch (IOException e) {
			fail("This method is suppose to get passed");
		}
		
		assertNotNull(words);
		assertEquals(75, words.size());
		System.out.println(words);
	}
	
	@Test(expected = IOException.class)
	public void testAllWordsForInvalidFile() throws IOException {
		FileParserHelper.getAllWords(Paths.get("/root/new.txt"));
		fail("This method is not suppose to get passed");
	}
	
	@Test
	public void testAllWordsForNullObject() throws IOException {
		List<String> words = FileParserHelper.getAllWords(null);
		assertNull(words);
	}
}
