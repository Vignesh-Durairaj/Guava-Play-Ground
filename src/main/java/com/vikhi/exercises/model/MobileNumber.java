package com.vikhi.exercises.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MobileNumber {
	private String countryCode;
	private String number;
}
