package com.vikhi.test.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.vikhi.exercises.model.Address;
import com.vikhi.exercises.model.Company;
import com.vikhi.exercises.model.Employee;
import com.vikhi.exercises.model.MobileNumber;
import com.vikhi.exercises.model.Person;

@DisplayName("While creating a new Model object")
public class PojoModelTest {

	private static Stream<Arguments> classProvider() {
		return Stream.of(
					Arguments.of(Person.class), 
					Arguments.of(MobileNumber.class),
					Arguments.of(Address.class),
					Arguments.of(Employee.class),
					Arguments.of(Company.class)
				);
	}
	
	@DisplayName("should have proper getter and setter and with correct references")
	@SuppressWarnings("unlikely-arg-type")
	@ParameterizedTest(name = "{index} => clazz= {0}")
	@MethodSource("classProvider")
	void testGettersAndSetters(Class<?> clazz) {
		PojoClass pojo = PojoClassFactory.getPojoClass(clazz);
		Validator validator = ValidatorBuilder
				.create()
				.with(new SetterTester(), new GetterTester())
				.build();
		
		validator.validate(pojo);
		assertNotNull(pojo.getClass().hashCode());
		assertNotNull(pojo.getClass().toString());
		assertTrue(pojo.getClass().toString() instanceof String);
		assertFalse(pojo.getClass().equals(PojoClassFactory.getPojoClass(clazz)));
	}
}
