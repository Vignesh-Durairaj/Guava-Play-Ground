package com.vikhi.test.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vikhi.exercises.dao.PersonDao;
import com.vikhi.exercises.model.Person;

public class BaseTest {

	private PersonDao personDao = mock(PersonDao.class);
	
	protected final Logger LOGGER = LogManager.getLogger(getClass());
	
	public void initData() {
		List<Person> persons = new ArrayList<>();
		persons.add(mock(Person.class));
		persons.add(mock(Person.class));
		persons.add(mock(Person.class));
		
		when(personDao.getPersons()).thenReturn(persons);
	}
	
	public List<Person> getPersons() {
		return personDao.getPersons();
	}
}
