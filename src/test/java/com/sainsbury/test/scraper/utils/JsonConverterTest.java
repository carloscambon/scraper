package com.sainsbury.test.scraper.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sainsbury.test.scraper.beans.Product;
import com.sainsbury.test.scraper.beans.ProductsResponse;

public class JsonConverterTest {

	@Test
	public void emptyProductListConvertsToJson() throws IOException {
		List<Product> productList = new ArrayList<Product>();
		ProductsResponse productsResponse = new ProductsResponse(productList);
		String jsonReturned = JsonConverter.getJson(productsResponse);
		ProductsResponse result = JsonConverter.getObject(jsonReturned);
		org.junit.Assert.assertTrue(result.getTotal() == 0);
		org.junit.Assert.assertTrue(result.getResults().isEmpty());
	}

	@Test
	public void filledProductListConvertsToJson() throws IOException {
		List<Product> productList = new ArrayList<Product>();
		Product product = new Product("title", "18kb", 1.8f, "description");
		productList.add(product);
		ProductsResponse productsResponse = new ProductsResponse(productList);
		String jsonReturned = JsonConverter.getJson(productsResponse);
		ProductsResponse result = JsonConverter.getObject(jsonReturned);
		org.junit.Assert.assertTrue(result.getTotal() == 1.8f);
		org.junit.Assert.assertTrue(result.getResults().size() == 1);
	}
}
