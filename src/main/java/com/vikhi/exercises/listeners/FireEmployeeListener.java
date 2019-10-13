package com.vikhi.exercises.listeners;

import com.google.common.eventbus.Subscribe;
import com.vikhi.exercises.event.EmployeeEvent;
import com.vikhi.exercises.model.Employee;

public class FireEmployeeListener {

	@Subscribe
	public void fireAnEmployee(EmployeeEvent event) {
		Employee employee = event.getEmployee();
		event.getAddressBook().removeAll(Long.valueOf(employee.getId()));
	}
}
