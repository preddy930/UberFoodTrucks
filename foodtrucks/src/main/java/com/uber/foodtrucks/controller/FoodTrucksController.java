package com.uber.foodtrucks.controller;

import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uber.foodtrucks.service.json.FoodTrucksJsonResponse;
import com.uber.foodtrucks.service.json.JsonResponse;

@Controller
@RequestMapping("/getinfo")
public class FoodTrucksController {
	
	private static final Logger logger = Logger.getLogger(FoodTrucksController.class);	
	
	private Cache uberCache;

	public Cache getUberCache() {
		return uberCache;
	}

	public void setUberCache(Cache uberCache) {
		this.uberCache = uberCache;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<JsonResponse> getFoodTruckData() {
		
		JsonResponse response;
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		responseHeaders.set("Access-Control-Allow-Methods", "GET");
		responseHeaders.set("Access-Control-Allow-Headers", "Content-Type");
		responseHeaders.set("Access-Control-Max-Age", "86400");
		
		Object locations = uberCache.get("foodtrucks").getValue();
		
		if (locations!=null && locations.toString().length()>0) {
		
			String loc = locations.toString();
			loc = loc.replaceAll("\\\"", "\"");  
			loc = loc.replaceAll("\t", "");  
			loc = loc.replaceAll("\n", "");
		
			response = new FoodTrucksJsonResponse ();
			response.setError(00000);
			((FoodTrucksJsonResponse) response).setFoodTrucksResponse(loc);
			return new ResponseEntity<JsonResponse>(response, responseHeaders, HttpStatus.OK);
			
		} else {
			response = new JsonResponse();
			response.setError(JsonResponse.CACHE_EMPTY);
			return new ResponseEntity<JsonResponse>(response, responseHeaders, HttpStatus.OK);
		}
	}
}