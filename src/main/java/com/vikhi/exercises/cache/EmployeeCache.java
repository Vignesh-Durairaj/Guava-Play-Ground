package com.vikhi.exercises.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vikhi.exercises.dao.EmployeeDao;
import com.vikhi.exercises.model.Employee;

public class EmployeeCache {
	
	private EmployeeDao employeeDao;

	public LoadingCache<Integer, Employee> getSimpleEmployeeCache() {
		CacheLoader<Integer, Employee> simpleEmployeeCacheLoader = 
				new CacheLoader<Integer, Employee>() {

					@Override
					public Employee load(Integer id) throws Exception {
						return employeeDao.getEmployeeById(id);
					}
		};
		
		return CacheBuilder.newBuilder().build(simpleEmployeeCacheLoader);
	}
}
