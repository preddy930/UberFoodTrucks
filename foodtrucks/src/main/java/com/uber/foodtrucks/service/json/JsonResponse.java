package com.uber.foodtrucks.service.json;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JsonResponse {
	
	public static final int SUCCESS = 00000;
	public static final int ERROR_REACHING_DATASOURCE = 10001;
	public static final int EMPTY_DATA_ERROR = 10002;
	public static final int CACHE_EMPTY = 10003;
	
	private String errorString;
	private int errorCode;
	
	private static final Map<Integer, String> errorMap;
	
	
	static {
        Map<Integer, String> aMap = new HashMap <Integer, String>();
        
        aMap.put(SUCCESS, "success");
        aMap.put(ERROR_REACHING_DATASOURCE,"Could not make connection to retrieve food truck data");
        aMap.put(EMPTY_DATA_ERROR, "Data empty");
        aMap.put(CACHE_EMPTY, "cache empty");
        
        errorMap = Collections.unmodifiableMap(aMap);
    }

	public JsonResponse() {
		errorString = "";
	}
	
	public void setError(int errorCode) {
		this.errorCode = errorCode;
		this.errorString = errorMap.get(errorCode);
	}

	public String getErrorString() {
		return errorString;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
