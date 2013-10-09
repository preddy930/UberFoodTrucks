package com.uber.foodtrucks.service;

import java.util.HashMap;
import java.util.Map;

import com.uber.server.exception.UberHttpClientException;
import com.uber.server.exception.UberResponseException;
import com.uber.util.UberHttpClient;

public class FoodTrucksDataResolver{

	private UberHttpClient client;
	
	private Map<String,String> params = new HashMap<String,String>() {
		{
			/*can add params if necessary
				put("key", "value");
			*/
		}
	};

	public void setClient(UberHttpClient client) {
		this.client = client;
	}
	
	public String getFoodTruckInfo() throws UberResponseException, UberHttpClientException {
		
		String foodtruckresponse = null;
		
		foodtruckresponse = client.getRequest(FoodTruckConstants.FOODTRUCKDOMAIN, FoodTruckConstants.FOODTRUCKSERVLETPATH, params, false);
		
		if (foodtruckresponse!=null && foodtruckresponse.length()>0) {
			return foodtruckresponse;
			
		} else {
			throw new UberResponseException(foodtruckresponse);
		}
			
	}
}
