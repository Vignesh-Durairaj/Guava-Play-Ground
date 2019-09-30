package com.vikhi.test.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.vikhi.exercises.collections.ImmutableListExercise;
import com.vikhi.exercises.model.Person;

public class GuavaImmutableListTest extends BaseTest {

	private List<Person> persons;
	private ImmutableListExercise multiListGenerator;
	
	
	@Before
	public void init() {
		multiListGenerator = new ImmutableListExercise();
		initData();
		persons = getPersons();
	}
	
	@Test
	public void testImmutableListObject() {
		List<Person> immutablePersonList = multiListGenerator.getImmutableList(persons.get(0), persons.get(2), persons.get(1));
		assertNotNull(immutablePersonList);
		assertThat(immutablePersonList.size(), is(3));
		immutablePersonList.stream().forEach(LOGGER::debug);
		assertSame(persons.get(0), immutablePersonList.get(0));
		assertSame(persons.get(1), immutablePersonList.get(2));
		assertSame(persons.get(2), immutablePersonList.get(1));
		assertTrue(persons.get(0).toString() instanceof String);
		
		immutablePersonList
			.stream()
			.peek(person -> assertNotNull(person.toString()))
			.peek(person -> assertNotNull(person.hashCode()))
			.peek(person -> assertEquals(String.class, person.toString().getClass()))
			.peek(person -> LOGGER.debug(person.hashCode()))
			.forEach(person -> assertTrue(person.hashCode() >= Integer.MIN_VALUE));
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testImmutabilityOfTheList() {
		List<Person> newImmutableList = ImmutableList.copyOf(persons);
		newImmutableList.add(mock(Person.class));
		fail("The above operation should trow an exception, but not!");
	}
	
	@Test
	public void testManipulationofImmutableList() {
		List<Person> newImmutableList = ImmutableList.copyOf(persons);
		persons.set(2, mock(Person.class));
		try {
			newImmutableList.set(1, mock(Person.class));
			fail("This operation is suppose to throw an exception !");
		} catch (UnsupportedOperationException e) {
			LOGGER.debug("This operation is not supported");
		}
	}
	
	@Test
	public void testIndirectManipulationIsAvoided() {
		List<Person> anotherList = persons;
		List<Person> javaImmutableList = Collections.unmodifiableList(persons);
		List<Person> guavaImmutableList = ImmutableList.copyOf(persons);
		assertEquals(3, javaImmutableList.size());
		assertEquals(3, guavaImmutableList.size());
		
		anotherList.add(mock(Person.class));
		assertEquals(4, javaImmutableList.size());
		assertEquals(3, guavaImmutableList.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullContentForImmutableList() {
		List<Person> anotherList = persons;
		persons.add(null);
		persons.add(mock(Person.class));
		assertEquals(5, anotherList.size());
		
		ImmutableList.copyOf(persons);
		fail("Guava immutable list throws exception while copying a NULL element as a list content!");
	}
}
