package com.vikhi.exercises.event;

import com.google.common.collect.Multimap;
import com.vikhi.exercises.model.Employee;

public class EmployeeEvent {

	private Employee employee;
	private Multimap<Long, String> addressBook;

	public EmployeeEvent(Employee employee, Multimap<Long, String> addressBook) {
		this.employee = employee;
		this.addressBook = addressBook;
	}

	public Employee getEmployee() {
		return employee;
	}

	public Multimap<Long, String> getAddressBook() {
		return addressBook;
	}
	
}
