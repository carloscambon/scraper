package com.sainsbury.test.scraper.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsbury.test.scraper.beans.ProductsResponse;

/**
 * Utility for conversions between java objects and Json strings
 * 
 * @author Carlos Cambon
 *
 */
public class JsonConverter {

	public static String getJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	}

	public static ProductsResponse getObject(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, ProductsResponse.class);
	}
}
