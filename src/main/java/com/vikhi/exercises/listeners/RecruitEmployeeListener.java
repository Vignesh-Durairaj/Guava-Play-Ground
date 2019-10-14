package com.vikhi.exercises.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;
import com.vikhi.exercises.event.EmployeeRecruitmentEvent;
import com.vikhi.exercises.model.Employee;

public class RecruitEmployeeListener {
	
	private static final Logger LOGGER = LogManager.getLogger(RecruitEmployeeListener.class);
	
	@Subscribe
	public void recruitNewEmployee(EmployeeRecruitmentEvent event) {
		Employee employee = event.getEmployee();
		employee
			.getMobileNumbers()
			.stream()
			.map(mn -> mn.getCountryCode() + " - " + mn.getNumber())
			.forEach(mn -> event.getAddressBook().put(employee.getId(), mn));
		LOGGER.info("Company phonebook updated to size " + event.getAddressBook().size() + " !");
	}

}