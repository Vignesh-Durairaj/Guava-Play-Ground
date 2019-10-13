package com.vikhi.exercises.listeners;

import com.google.common.eventbus.Subscribe;
import com.vikhi.exercises.event.EmployeeTerminationEvent;
import com.vikhi.exercises.model.Employee;

public class FireEmployeeListener {

	@Subscribe
	public void fireAnEmployee(EmployeeTerminationEvent event) {
		Employee employee = event.getEmployee();
		event.getAddressBook().removeAll(Long.valueOf(employee.getId()));
	}
}
