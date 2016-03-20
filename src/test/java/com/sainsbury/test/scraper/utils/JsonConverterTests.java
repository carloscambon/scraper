package com.sainsbury.test.scraper.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sainsbury.test.scraper.beans.Product;
import com.sainsbury.test.scraper.beans.ProductsResponse;

public class JsonConverterTests {

	@Test
	public void emptyProductListConvertsToJson () throws JsonProcessingException {
        List<Product> productList = new ArrayList<Product>();
 		ProductsResponse productsResponse = new ProductsResponse(productList);
 		String actual = JsonConverter.getJson(productsResponse);
 		String expected = "{\"results\":[],\"total\":0.0}";
 		assertThat(actual, is(expected));
	}
	
	@Test
	public void nullProductListConvertsToJson () throws JsonProcessingException {
        List<Product> productList = new ArrayList<Product>();
 		ProductsResponse productsResponse = new ProductsResponse(productList);
 		String actual = JsonConverter.getJson(productsResponse);
 		String expected = "{\"results\":[],\"total\":0.0}";
 		assertThat(actual, is(expected));
	}
	
	@Test
	public void filledProductListConvertsToJson () throws JsonProcessingException {
        List<Product> productList = new ArrayList<Product>();
        Product product = new Product("title", "18kb", 1.8f, "description");
		productList.add(product );
 		ProductsResponse productsResponse = new ProductsResponse(productList);
 		String actual = JsonConverter.getJson(productsResponse);
 		String expected = "{\"results\":[{\"title\":\"title\",\"size\":\"18kb\",\"unit_price\":1.8,\"description\":\"description\"}],\"total\":1.8}";
 		assertThat(actual, is(expected));
	}
}
