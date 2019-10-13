package com.vikhi.test.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vikhi.exercises.event.GlobalEventBus;
import com.vikhi.exercises.listeners.FinalSettlementListener;
import com.vikhi.exercises.listeners.RecruitEmployeeListener;
import com.vikhi.exercises.listeners.TerminateEmployeeListener;
import com.vikhi.exercises.model.Company;
import com.vikhi.exercises.model.Employee;

public class EventBusTest extends BaseTest {

	private List<Employee> employees;
	private Company company;
	private static RecruitEmployeeListener addEmployeeListener = new RecruitEmployeeListener();
	private static TerminateEmployeeListener removeEmployeeListener = new TerminateEmployeeListener();
	private static FinalSettlementListener calculateSettlementListener = new FinalSettlementListener();
	
	@BeforeEach
	void init() {
		initData();
		employees = employeeDao.getAllEmployees();
	}
	
	@Test
	void testAddingNewEmployee() {
		GlobalEventBus.getInstance().register(addEmployeeListener);
		Employee employeeOne = employees.get(0);
		company = new Company(1L, "ABC Private Limited");

		company.addEmployee(employeeOne);
		assertThat(company.getEmployees().size(), is(1));
		assertThat(company.getPhoneBook().size(), is(2));
		assertThat(company.getPhoneBook().get(employeeOne.getId()).size(), is(2));
		
		Employee employeeTwo = employees.get(1);
		company.addEmployee(employeeTwo);
		assertThat(company.getEmployees().size(), is(2));
		assertThat(company.getPhoneBook().size(), is(3));
		assertThat(company.getPhoneBook().get(employeeTwo.getId()).size(), is(1));
	}
	
	@Test
	void terminateAnEmployee() {
		GlobalEventBus.getInstance().register(addEmployeeListener);
		GlobalEventBus.getInstance().register(removeEmployeeListener);
		company = new Company(1L, "ABC Private Limited");
		company.addEmployee(employees.get(0));
		company.addEmployee(employees.get(1));
		assertThat(company.getEmployees().size(), is(2));
		assertThat(company.getPhoneBook().size(), is(3));
		assertThat(company.getPhoneBook().get(employees.get(1).getId()).size(), is(1));
		
		company.removeEmployee(employees.get(1));
		assertThat(company.getEmployees().size(), is(1));
		assertThat(company.getPhoneBook().size(), is(2));
		assertThat(company.getPhoneBook().get(employees.get(1).getId()).size(), is(0));
		
		company.removeEmployee(employees.get(0));
		assertThat(company.getEmployees().size(), is(0));
		assertThat(company.getPhoneBook().size(), is(0));
	}
	
	@Test
	void fireAnEmployee() {
		GlobalEventBus.getInstance().register(addEmployeeListener);
		GlobalEventBus.getInstance().register(removeEmployeeListener);
		GlobalEventBus.getInstance().register(calculateSettlementListener);
		company = new Company(1L, "ABC Private Limited");
		company.addEmployee(employees.get(0));
		company.addEmployee(employees.get(1));
		assertThat(company.getEmployees().size(), is(2));
		assertThat(company.getPhoneBook().size(), is(3));
		assertThat(company.getPhoneBook().get(employees.get(1).getId()).size(), is(1));
		
		company.removeEmployee(employees.get(1));
		assertThat(company.getEmployees().size(), is(1));
		assertThat(company.getPhoneBook().size(), is(2));
		assertThat(company.getPhoneBook().get(employees.get(1).getId()).size(), is(0));
		
		company.removeEmployee(employees.get(0));
		assertThat(company.getEmployees().size(), is(0));
		assertThat(company.getPhoneBook().size(), is(0));
	}
}
