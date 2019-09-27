package com.vikhi.exercises.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person extends ModelBase {
	private String firstName;
	private String lastName;
	private int age;
}
