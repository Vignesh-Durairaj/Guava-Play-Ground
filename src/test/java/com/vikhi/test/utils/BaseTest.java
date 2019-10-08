package com.vikhi.test.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vikhi.exercises.dao.EmployeeDao;
import com.vikhi.exercises.dao.MobileNumberDao;
import com.vikhi.exercises.dao.PersonDao;
import com.vikhi.exercises.model.MobileNumber;
import com.vikhi.exercises.model.Person;

public class BaseTest {

	private PersonDao personDao = mock(PersonDao.class);
	private EmployeeDao employeeDao = mock(EmployeeDao.class);
	private MobileNumberDao mobileNumDao = mock(MobileNumberDao.class);
	
	protected final Logger LOGGER = LogManager.getLogger(getClass());
	
	public void initData() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Vignesh", "Durairaj", 32));
		persons.add(new Person(null, "Durairaj", 32));
		persons.add(new Person("Vignesh", null, 32));
		
		when(personDao.getPersons()).thenReturn(persons);
		
		List<MobileNumber> mobileNumbers = new ArrayList<>();
		mobileNumbers.add(new MobileNumber("91", "96983 46167"));
		mobileNumbers.add(new MobileNumber("91", "94439 38090"));
		mobileNumbers.add(new MobileNumber("81", "98 2364 7845"));
		mobileNumbers.add(new MobileNumber("852", "1 4567 8974"));
		mobileNumbers.add(new MobileNumber("1", "121 4574 5698"));
		mobileNumbers.add(new MobileNumber("1", "982 7456 3240"));
	}
	
	public List<Person> getPersons() {
		return personDao.getPersons();
	}
}
