package com.uber.server.exception;

import java.net.URI;

import org.apache.log4j.Logger;

public class UberHttpClientException extends Exception {

	private static final Logger logger = Logger.getLogger(UberHttpClientException.class);
	
	public UberHttpClientException(Exception e, URI uri) {
		
		logger.debug("Caught http client exception when trying to reach this URI:\n"+uri.toString(), e);
	}
}
