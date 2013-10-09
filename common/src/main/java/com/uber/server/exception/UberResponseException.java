package com.uber.server.exception;

import org.apache.log4j.Logger;

public class UberResponseException extends Exception {

	private static final Logger logger = Logger.getLogger(UberResponseException.class);
			
	public UberResponseException(String response) {
		
		logger.debug("Response from external service is invalid: " + response + "\n");
	}
}
