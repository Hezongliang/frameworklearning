package com.ztesoft.frameworklearning.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SpringListener {
    protected static final Logger logger = LoggerFactory.getLogger(SpringListener.class.getName());
    
	public void springStartup() {
	    logger.info("application start");
	}

	public void springEnd() {
	    logger.info("application stop");
	}
}
