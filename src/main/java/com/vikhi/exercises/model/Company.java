package com.vikhi.exercises.model;

import java.util.List;
import java.util.Map;

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
	private List<Employee> employees;
	private Map<Long, String> phoneBook;
}
