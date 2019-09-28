package com.vikhi.test.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.vikhi.exercises.collections.MultiListExercise;
import com.vikhi.exercises.model.Person;

public class MultiListTest {

	private List<Person> persons;
	private MultiListExercise multiListGenerator;
	
	@Before
	public void init() {
		multiListGenerator = new MultiListExercise();
		persons = new ArrayList<>();
		persons.add(new Person("Vignesh", "Durairaj", 32));
		persons.add(new Person("Venugopal", "Nadakuditi", 37));
		persons.add(new Person("Narasimhan", "Pradeep", 39));
	}
	
	@Test
	public void testImmutableList() {
		List<Person> immutablePersonList = multiListGenerator.getImmutableList(persons.get(0), persons.get(2), persons.get(1));
		assertNotNull(immutablePersonList);
		assertThat(immutablePersonList.size(), is(3));
		immutablePersonList.stream().forEach(System.out::println);
		assertSame(persons.get(0), immutablePersonList.get(0));
		assertSame(persons.get(1), immutablePersonList.get(2));
		assertSame(persons.get(2), immutablePersonList.get(1));
		
		immutablePersonList
			.stream()
			.peek(person -> assertNotNull(person.toString()))
			.peek(person -> assertNotNull(person.hashCode()))
			.peek(person -> assertEquals(String.class, person.toString().getClass()))
			.peek(person -> System.out.println(person.hashCode()))
			.forEach(person -> assertTrue(person.hashCode() >= Integer.MIN_VALUE));
	}
}
