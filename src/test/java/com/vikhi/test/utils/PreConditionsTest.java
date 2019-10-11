package com.vikhi.test.utils;

import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.vikhi.exercises.model.Employee;

@DisplayName("While navigating to the list of employees")
public class PreConditionsTest extends BaseTest {

	List<Employee> employees;
	
	@BeforeEach
	void init() {
		initData();
		employees = employeeDao.getAllEmployees();
	}
	
	@DisplayName("should have a valid personal details")
	@Test
	void testForValidEmployeeWithDetails() {
		Exception exc;
		Employee employeeOne = employees.get(0);
		Employee employeeTwo = employees.get(1);
		
		checkNotNull(employeeOne.getDetails());
		
		employeeTwo.setDetails(null);
		exc = assertThrows(NullPointerException.class, () -> checkNotNull(employeeTwo.getDetails()));
		assertNull(exc.getMessage());
		
		String msg = "Employee's personal Details should not be null";
		exc = assertThrows(NullPointerException.class, () -> checkNotNull(employeeTwo.getDetails(), msg));
		assertEquals(msg, exc.getMessage());
	}
	
	@DisplayName("Should have atleast one mobile number")
	@Test
	void testForMobileNumbers() {
		Exception exc;
		Employee employeeOne = employees.get(0);
		Employee employeeTwo = employees.get(1);
		
		checkElementIndex(0, employeeOne.getMobileNumbers().size());
		
		employeeTwo.setMobileNumbers(new ArrayList<>());
		exc = assertThrows(IndexOutOfBoundsException.class, () -> checkElementIndex(0, employeeTwo.getMobileNumbers().size()));
		assertEquals("index (0) must be less than size (0)", exc.getMessage());
		
		String msg = "No mobile number found for this employee";
		employeeTwo.setMobileNumbers(new ArrayList<>());
		exc = assertThrows(IndexOutOfBoundsException.class, () -> checkElementIndex(0, employeeTwo.getMobileNumbers().size(), msg));
		assertTrue(exc.getMessage().contains(msg));
	}
	
}
