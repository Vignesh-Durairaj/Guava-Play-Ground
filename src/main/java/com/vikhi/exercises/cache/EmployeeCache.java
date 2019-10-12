package com.vikhi.exercises.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vikhi.exercises.dao.EmployeeDao;
import com.vikhi.exercises.model.Employee;

public class EmployeeCache {
	
	private EmployeeDao employeeDao;

	public LoadingCache<Long, Employee> getSimpleEmployeeCache() {
		CacheLoader<Long, Employee> simpleEmployeeCacheLoader = 
				new CacheLoader<Long, Employee>() {

					@Override
					public Employee load(Long id) throws Exception {
						return employeeDao.getEmployeeById(id);
					}
		};
		
		return CacheBuilder.newBuilder().build(simpleEmployeeCacheLoader);
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
}
