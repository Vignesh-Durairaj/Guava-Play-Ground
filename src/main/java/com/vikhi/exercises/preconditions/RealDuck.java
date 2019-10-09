package com.vikhi.exercises.preconditions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealDuck implements Bird {

	private final Logger LOGGER = LogManager.getLogger(this.getClass());
	
	@Override
	public void fly() {
		LOGGER.info("And I'm flying");
	}

	@Override
	public void makeSound() {
		LOGGER.info("Quack Quack");
	}

}
