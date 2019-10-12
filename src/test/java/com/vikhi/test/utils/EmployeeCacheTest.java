package com.vikhi.test.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.cache.LoadingCache;
import com.vikhi.exercises.cache.EmployeeCache;
import com.vikhi.exercises.model.Employee;

@DisplayName("While trying to setup a data-cache for employees")
public class EmployeeCacheTest extends BaseTest{

	private EmployeeCache empCache;
	private Employee employee;
	
	@BeforeEach
	void init() {
		initData();
		empCache = new EmployeeCache();
		empCache.setEmployeeDao(employeeDao);
	}
	
	@Test
	void testSimpleEmployeeCache() {
		LoadingCache<Long, Employee> cache = empCache.getSimpleEmployeeCache();
		assertEquals(0, cache.size());
		employee = cache.getUnchecked(1L);
		assertNotNull(employee);
		assertEquals(1, cache.size());
		assertEquals(1, employee.getId());
		employee = cache.getUnchecked(2L);
		assertNotNull(employee);
		assertEquals(2, cache.size());
		employee = cache.getUnchecked(1L);
		assertNotNull(employee);
		assertEquals(2, cache.size());
	}
}
