package com.vikhi.exercises.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee {
	
	private long id;
	private Person details;
	private Address address;
	private List<MobileNumber> mobileNumbers;
	private long salary;
}
