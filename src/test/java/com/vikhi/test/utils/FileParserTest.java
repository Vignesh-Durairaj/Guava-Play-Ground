package com.vikhi.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.vikhi.exercises.utils.FileParserHelper;

public class FileParserTest {
	
	public static final Logger LOGGER = LogManager.getLogger(FileParserTest.class);

	@Test
	public void testAllWordsInFile() {
		List<String> words = null;
		try {
			words = FileParserHelper.getAllWords(Paths.get("src/resources/twister.txt"));
		} catch (IOException e) {
			fail("This method is suppose to get passed");
		}
		
		assertNotNull(words);
		assertEquals(75, words.size());
		LOGGER.info(words);
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
