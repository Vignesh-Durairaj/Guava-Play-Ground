package com.vikhi.exercises.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;
import com.vikhi.exercises.event.EmployeeTerminationEvent;
import com.vikhi.exercises.model.Employee;

public class TerminateEmployeeListener {

	private static final Logger LOGGER = LogManager.getLogger(TerminateEmployeeListener.class);
	
	@Subscribe
	public void terminateAnEmployee(EmployeeTerminationEvent event) {
		Employee employee = event.getEmployee();
		event.getAddressBook().removeAll(Long.valueOf(employee.getId()));
		LOGGER.info(String.format("Employee %s has been removed from the company", employee.getDetails().getFirstName() + " - " + employee.getDetails().getLastName()));
	}
}
