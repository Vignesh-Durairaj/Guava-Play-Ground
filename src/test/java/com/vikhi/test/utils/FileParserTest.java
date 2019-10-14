package com.vikhi.test.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.vikhi.exercises.utils.FileParserHelper;

@DisplayName("While parsing the input file into a list of words in it")
public class FileParserTest extends BaseTest {
	
	@DisplayName("Should contain all the words from a valid file")
	@Test
	void testAllWordsInFile() {
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
	
	@DisplayName("Should throw exception, while parsing an invalid file")
	@Test
	void testAllWordsForInvalidFile() throws IOException {
		assertThrows(IOException.class, () -> FileParserHelper.getAllWords(Paths.get("/root/new.txt")));
	}
	
	@DisplayName("Should throw exception, when NULL is specified as input file")
	@Test
	void testAllWordsForNullObject() throws IOException {
		List<String> words = FileParserHelper.getAllWords(null);
		assertNull(words);
	}
}
