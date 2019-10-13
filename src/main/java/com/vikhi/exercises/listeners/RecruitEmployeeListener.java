package com.vikhi.exercises.listeners;

import com.google.common.eventbus.Subscribe;
import com.vikhi.exercises.event.EmployeeEvent;
import com.vikhi.exercises.model.Employee;

public class RecruitEmployeeListener {
	
	@Subscribe
	public void recruitNewEmployee(EmployeeEvent event) {
		Employee employee = event.getEmployee();
		employee
			.getMobileNumbers()
			.stream()
			.map(mn -> mn.getCountryCode() + " - " + mn.getNumber())
			.forEach(mn -> event.getAddressBook().put(employee.getId(), mn));
	}

}
