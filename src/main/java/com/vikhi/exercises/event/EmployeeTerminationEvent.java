package com.vikhi.exercises.event;

import com.google.common.collect.Multimap;
import com.vikhi.exercises.model.Employee;

public class EmployeeTerminationEvent {

	private Employee employee;
	private Multimap<Long, String> addressBook;

	public EmployeeTerminationEvent(Employee employee, Multimap<Long, String> phoneBook) {
		this.employee = employee;
		this.addressBook = phoneBook;
	}

	public Employee getEmployee() {
		return employee;
	}

	public Multimap<Long, String> getAddressBook() {
		return addressBook;
	}
}
