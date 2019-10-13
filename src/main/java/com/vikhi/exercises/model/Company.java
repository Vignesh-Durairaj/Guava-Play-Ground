package com.vikhi.exercises.model;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.vikhi.exercises.event.EmployeeRecruitmentEvent;
import com.vikhi.exercises.event.EmployeeTerminationEvent;
import com.vikhi.exercises.event.GlobalEventBus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Company {

	@NonNull final private Long id;
	@NonNull final private String name;
	private List<Employee> employees = new ArrayList<>();
	private Multimap<Long, String> phoneBook = MultimapBuilder.hashKeys().arrayListValues().build();
	
	public void addEmployee(Employee newEmployee) {
		employees.add(newEmployee);
		EmployeeRecruitmentEvent event = new EmployeeRecruitmentEvent(newEmployee, phoneBook);
		GlobalEventBus.getInstance().post(event);
	}
	
	public void fireEmployee(Employee employeeToBeRemoved) {
		employees.remove(employeeToBeRemoved);
		EmployeeTerminationEvent event = new EmployeeTerminationEvent(employeeToBeRemoved, phoneBook);
		GlobalEventBus.getInstance().post(event);
	}
}
