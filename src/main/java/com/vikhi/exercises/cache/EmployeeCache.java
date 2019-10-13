package com.vikhi.exercises.cache;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
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
	
	private RemovalListener<Long, Employee> employeeRemovalListener = 
			new RemovalListener<Long, Employee>() {
				
				@Override
				public void onRemoval(RemovalNotification<Long, Employee> removalEvent) {
					LOGGER.info(String.format("Employee of ID %s removed owing to the reason '%s'", 
							removalEvent.getKey().longValue(), 
							removalEvent.getCause().name()));
				}
			};

	public LoadingCache<Long, Employee> getSimpleEmployeeCache() {
		return CacheBuilder
				.newBuilder()
				.removalListener(employeeRemovalListener)
				.build(simpleEmployeeCacheLoader);
	}

	public LoadingCache<Long, Employee> getSizeLimitedCache(final int maxCacheSize) {
		return CacheBuilder
				.newBuilder()
				.maximumSize(maxCacheSize)
				.removalListener(employeeRemovalListener)
				.build(simpleEmployeeCacheLoader);
	}
	
	public LoadingCache<Long, Employee> getTimeLimitedCache(final int millis) {
		return CacheBuilder
				.newBuilder()
				.expireAfterAccess(millis, TimeUnit.MILLISECONDS)
				.removalListener(employeeRemovalListener)
				.build(simpleEmployeeCacheLoader);
	}
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
}
