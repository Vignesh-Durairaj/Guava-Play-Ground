package com.vikhi.exercises.collections;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.vikhi.exercises.model.Person;

public class MultiListExercise {
	
	public List<Person> getImmutableList(Person ...persons) {
		return ImmutableList.copyOf(persons);
	}

}
