package com.vikhi.test.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.vikhi.exercises.model.Person;

@RunWith(Parameterized.class)
public class PojoModelTest {

	private Class<?> clazz;
	
	public PojoModelTest(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Parameters
	public static Collection<Object[]> params() {
		return Arrays.asList(new Object[][] {
			{Person.class}
		});
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testGettersAndSetters() {
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
