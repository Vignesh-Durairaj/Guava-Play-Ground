package com.vikhi.exercises.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;
import com.vikhi.exercises.event.EmployeeTerminationEvent;
import com.vikhi.exercises.model.Employee;

public class FinalSettlementListener {

	private static final Logger LOGGER = LogManager.getLogger(FinalSettlementListener.class);
	
	@Subscribe
	public void fireAnEmployee(EmployeeTerminationEvent event) {
		Employee employee = event.getEmployee();
		LOGGER.info(String.format("Final settlemtent of INR %s/- has been processed for employee : ", event.getFinalSettlement(), employee.getDetails().getFirstName() + " - " + employee.getDetails().getLastName()));
	}
}
