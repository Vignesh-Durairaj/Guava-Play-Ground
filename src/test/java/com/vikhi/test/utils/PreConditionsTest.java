package com.vikhi.test.utils;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vikhi.exercises.model.Employee;

public class PreConditionsTest extends BaseTest {

	List<Employee> employees;
	
	@BeforeEach
	void init() {
		initData();
		employees = employeeDao.getAllEmployees();
	}
	
	@Test
	void testForValidEmployee() {
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
	
}
