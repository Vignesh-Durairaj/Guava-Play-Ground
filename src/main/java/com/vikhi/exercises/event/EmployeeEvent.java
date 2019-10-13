package com.vikhi.exercises.event;

import com.vikhi.exercises.model.Employee;

public class EmployeeEvent {

	private Employee employee;

	public EmployeeEvent(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}
	
}
