package com.vikhi.exercises.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vikhi.exercises.dao.EmployeeDao;
import com.vikhi.exercises.model.Employee;

public class EmployeeCache {
	
	private static final Logger LOGGER = LogManager.getLogger(EmployeeCache.class);
	private EmployeeDao employeeDao;
	private CacheLoader<Long, Employee> simpleEmployeeCacheLoader = 
				new CacheLoader<Long, Employee>() {
	
			@Override
			public Employee load(Long id) throws Exception {
				LOGGER.info("Fresh fetch of data from source, for employee id : " + id);
				return employeeDao.getEmployeeById(id);
			}
	};

	public LoadingCache<Long, Employee> getSimpleEmployeeCache() {
		return CacheBuilder
				.newBuilder()
				.build(simpleEmployeeCacheLoader);
	}

	public LoadingCache<Long, Employee> getSizeLimitedCache(final int maxCacheSize) {
		return CacheBuilder
				.newBuilder()
				.maximumSize(maxCacheSize)
				.build(simpleEmployeeCacheLoader);
	}
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
}
