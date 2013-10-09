package com.uber.foodtrucks.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uber.foodtrucks.service.FoodTrucksDataResolver;
import com.uber.foodtrucks.service.json.JsonResponse;
import com.uber.server.exception.UberHttpClientException;
import com.uber.server.exception.UberResponseException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

@Controller
@RequestMapping("/populate")
public class FoodTrucksDataController {
	
	private static final Logger logger = Logger.getLogger(FoodTrucksDataController.class);	
	private FoodTrucksDataResolver ftdr;
	
	private Cache uberCache;

	public Cache getUberCache() {
		return uberCache;
	}

	public void setUberCache(Cache uberCache) {
		this.uberCache = uberCache;
	}

	public FoodTrucksDataResolver getFtdr() {
		return ftdr;
	}

	public void setFtdr(FoodTrucksDataResolver ftdr) {
		this.ftdr = ftdr;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse getFoodTruckData() {
		
		JsonResponse response = new JsonResponse();
		
		try {
			
			String foodtruckresponse = ftdr.getFoodTruckInfo();
			uberCache.put(new Element("foodtrucks",foodtruckresponse));
			response.setError(JsonResponse.SUCCESS);
			return response;
		}catch (UberHttpClientException e) {
			logger.error("Error reaching datasource", e);
			response.setError(JsonResponse.ERROR_REACHING_DATASOURCE);
			return response;
		} catch (UberResponseException e) {
			logger.error("Response returned empty", e);
			response.setError(JsonResponse.EMPTY_DATA_ERROR);
			return response;
		}
	}
}