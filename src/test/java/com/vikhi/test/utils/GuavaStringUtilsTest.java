package com.vikhi.test.utils;

import static com.vikhi.exercises.utils.MyConstants.COLON;
import static com.vikhi.exercises.utils.MyConstants.COMMA;
import static com.vikhi.exercises.utils.MyConstants.PIPE_SEPARATOR;
import static com.vikhi.exercises.utils.MyConstants.SEMI_COLON;
import static com.vikhi.exercises.utils.MyConstants.WHITE_SPACE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.base.Joiner;

public class GuavaStringUtilsTest extends BaseTest{
	
	private List<Integer> ints;
	private Joiner joiner;
	
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
		
		
		
	}
}
