package com.vikhi.exercises.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.EventBus;

public class GlobalEventBus extends EventBus {
	
	private static final Logger LOGGER = LogManager.getLogger(GlobalEventBus.class);
	private static GlobalEventBus eventBus = null;
	
	private GlobalEventBus() {
		LOGGER.info("A new event bus created");
	}
	
	public static GlobalEventBus getInstance() {
		if (eventBus == null) {
			eventBus = new GlobalEventBus();
		}
		return eventBus;
	}

}
