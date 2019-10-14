package com.vikhi.test.utils;

import static com.vikhi.exercises.utils.ConstantsHelper.COLON;
import static com.vikhi.exercises.utils.ConstantsHelper.COMMA;
import static com.vikhi.exercises.utils.ConstantsHelper.PIPE_SEPARATOR;
import static com.vikhi.exercises.utils.ConstantsHelper.SEMI_COLON;
import static com.vikhi.exercises.utils.ConstantsHelper.WHITE_SPACE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.vikhi.exercises.utils.ConstantsHelper;

public class GuavaStringUtilsTest extends BaseTest{
	
	private List<Integer> ints;
	private Joiner joiner;
	private Splitter splitter;
	
	@BeforeEach
	void init() {
		initData();
	}

	@Test
	void testSimpleJoiner() {
		ints = List.of(1, 2, 3, 4, 5, 10);
		joiner = Joiner.on(PIPE_SEPARATOR);
		assertEquals("1|2|3|4|5|10", joiner.join(ints));
		
		joiner = Joiner.on(SEMI_COLON);
		assertEquals("1;2;3;4;5;10", joiner.join(ints));
		
		joiner = Joiner.on(COLON);
		assertEquals("1:2:3:4:5:10", joiner.join(ints));
		
		joiner = Joiner.on(COMMA + WHITE_SPACE);
		assertEquals("1, 2, 3, 4, 5, 10", joiner.join(ints));
		
		ints = Arrays.asList(1, 2, null, 3, 4, null);
		assertThrows(NullPointerException.class, () -> joiner.join(ints));
		
		joiner = joiner.skipNulls();
		assertEquals("1, 2, 3, 4", joiner.join(ints));
		
		joiner = Joiner.on(COMMA + WHITE_SPACE).useForNull("Some Null Value");
		assertEquals("1, 2, Some Null Value, 3, 4, Some Null Value", joiner.join(ints));
	}
	
	@Test
	void testComplexObjectJoiner() {
		List<Object> objects = List.of(mobileNumDao.getAllMobileNumbers().get(0), personDao.getPersons().get(0));
		Joiner joiner = Joiner.on(ConstantsHelper.COLON);
		String newString = mobileNumDao.getAllMobileNumbers().get(0).toString() + COLON + personDao.getPersons().get(0).toString();
		assertEquals(newString, joiner.join(objects));
	}
	
	@Test
	void testSplitter() {
		splitter = Splitter.on(COLON);
		assertThat(splitter.split("1::2:3:4::5:"), containsInAnyOrder("1","","2","3","4","","5",""));
		
		splitter = splitter.omitEmptyStrings();
		assertThat(splitter.split("1:: 2:3:4 ::5:"), containsInAnyOrder("1"," 2","3","4 ","5"));
		
		splitter = splitter.trimResults();
		assertThat(splitter.split("1:: 2:3:4 ::5:"), containsInAnyOrder("1","2","3","4","5"));
		
		splitter = Splitter.on(CharMatcher.anyOf(",:;_- ")).omitEmptyStrings();
		assertThat(splitter.split("a,b;c_d-e f g"), containsInAnyOrder("a", "b", "c", "d", "e", "f", "g"));
		
		splitter = Splitter.on(COMMA + WHITE_SPACE);
		assertThat(splitter.split("Vignesh, Durairaj, Tirupur"), containsInAnyOrder("Vignesh", "Durairaj", "Tirupur"));
		
		splitter = Splitter.fixedLength(3);
		assertThat(splitter.split("Vignesh"), containsInAnyOrder("Vig", "nes", "h"));
		
		splitter = Splitter
				.on(COMMA)
				.omitEmptyStrings()
				.trimResults()
				.limit(4);
		assertThat(splitter.split("1, 2, 3, 4, 5, 6, 7, 8"), containsInAnyOrder("1", "2", "3", "4, 5, 6, 7, 8"));
		
		assertThat(Splitter
				.on(COMMA)
				.trimResults(CharMatcher.is('_'))
				.split("_a ,_b_ ,c__"), 
				containsInAnyOrder("a ", "b_ ", "c"));
		
		assertTrue((splitter.splitToList("a, b, c , d,e")) instanceof List);
	}
}
