package com.vikhi.test.utils;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vikhi.exercises.dao.AddressDao;
import com.vikhi.exercises.dao.EmployeeDao;
import com.vikhi.exercises.dao.MobileNumberDao;
import com.vikhi.exercises.dao.PersonDao;
import com.vikhi.exercises.model.Address;
import com.vikhi.exercises.model.Employee;
import com.vikhi.exercises.model.MobileNumber;
import com.vikhi.exercises.model.Person;

public class BaseTest {

	protected PersonDao personDao = mock(PersonDao.class);
	protected MobileNumberDao mobileNumDao = mock(MobileNumberDao.class);
	protected AddressDao addressDao = mock(AddressDao.class);
	protected EmployeeDao employeeDao = mock(EmployeeDao.class);
	
	protected final Logger LOGGER = LogManager.getLogger(getClass());
	
	public void initData() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Vignesh", "Durairaj", 32));
		persons.add(new Person(null, "Durairaj", 60));
		persons.add(new Person("Vignesh", null, 32));
		when(personDao.getPersons()).thenReturn(persons);
		
		List<MobileNumber> mobileNumbers = new ArrayList<>();
		mobileNumbers.add(new MobileNumber("91", "96983 46167"));
		mobileNumbers.add(new MobileNumber("91", "94439 38090"));
		mobileNumbers.add(new MobileNumber("81", "98 2364 7845"));
		mobileNumbers.add(new MobileNumber("852", "1 4567 8974"));
		mobileNumbers.add(new MobileNumber("1", "121 4574 5698"));
		mobileNumbers.add(new MobileNumber("1", "982 7456 3240"));
		when(mobileNumDao.getAllMobileNumbers()).thenReturn(mobileNumbers);
		
		List<Address> addresses = new ArrayList<>();
		addresses.add(new Address("Pune", "411048"));
		addresses.add(new Address("Chennai", "600100"));
		addresses.add(new Address("Tokyo", "100-1103"));
		addresses.add(new Address("Hongkong", "N/A"));
		addresses.add(new Address("New York", "10041"));
		addresses.add(new Address("Los Angeles", "90014"));
		when(addressDao.getAddresses()).thenReturn(addresses);
		
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1, persons.get(0), addresses.get(0), List.of(mobileNumbers.get(0), mobileNumbers.get(1)), 20000L));
		employees.add(new Employee(2, persons.get(1), addresses.get(1), List.of(mobileNumbers.get(2)), 20000L));
		employees.add(new Employee(3, persons.get(2), addresses.get(2), List.of(mobileNumbers.get(3), mobileNumbers.get(4)), 0L));
		when(employeeDao.getAllEmployees()).thenReturn(employees);
		when(employeeDao.getEmployeeById(anyLong())).thenReturn(employees.get(0), employees.get(1), employees.get(2));
	}
	
}
