package com.vikhi.test.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;
import com.vikhi.exercises.collections.ImmutableListExercise;
import com.vikhi.exercises.model.Person;

@DisplayName("While creating an immutable List from antother source")
public class GuavaImmutableListTest extends BaseTest {

	private List<Person> persons;
	private ImmutableListExercise multiListGenerator;
	
	
	@BeforeEach
	void init() {
		multiListGenerator = new ImmutableListExercise();
		initData();
		persons = personDao.getPersons();
	}
	
	@DisplayName("should create an immutable from a var...args of object")
	@Test
	void testImmutableListObject() {
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
	
	@DisplayName("should throw exception while trying to add as element to the immutable list")
	@Test
	void testImmutabilityOfTheList() {
		assertThrows(UnsupportedOperationException.class, () -> {
			List<Person> newImmutableList = ImmutableList.copyOf(persons);
			newImmutableList.add(mock(Person.class));
		});
		
	}
	
	@DisplayName("should throw exception while trying to modify the contents of immutable list")
	@Test
	void testManipulationofImmutableList() {
		List<Person> newImmutableList = ImmutableList.copyOf(persons);
		persons.set(2, mock(Person.class));
		try {
			newImmutableList.set(1, mock(Person.class));
			fail("This operation is suppose to throw an exception !");
		} catch (UnsupportedOperationException e) {
			LOGGER.debug("This operation is not supported");
		}
	}
	
	@DisplayName("should not get mutated, while manipulating the underlying list via false reference")
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
	
	@DisplayName("should throw an NPE while trying to create an immutable list from another list containing NULL object")
	@Test
	public void testNullContentForImmutableList() {
		List<Person> anotherList = persons;
		persons.add(null);
		persons.add(mock(Person.class));
		assertEquals(5, anotherList.size());
		
		assertThrows(NullPointerException.class, () -> ImmutableList.copyOf(persons));
	}
}
