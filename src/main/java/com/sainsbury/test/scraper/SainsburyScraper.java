package com.sainsbury.test.scraper;

import java.util.List;

import org.jsoup.helper.Validate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sainsbury.test.scraper.beans.Product;
import com.sainsbury.test.scraper.beans.ProductsResponse;
import com.sainsbury.test.scraper.bussiness.SainsburyScraperBO;
import com.sainsbury.test.scraper.utils.JsonConverter;

/**
 * Utility to scrape a Sainsbury’s grocery site - Ripe Fruits page and returns a JSON array of all the products on the page. 
 * 
 * @author Carlos Cambon
 *
 */
public class SainsburyScraper {

	/**
	 * Scrapes the url and returns a list of products as a {@link ProductsResponse.java} in json format 
	 * @see ProductsResponse.java
	 * @param url
	 * @return json of List of products scraped
	 */
	static String scrapeUrl (String url) {
		String json = "";
		System.out.printf("Fetching %s...", url);
		SainsburyScraperBO bo = new SainsburyScraperBO();
		List<Product> productList = bo.getProductList(url);
		ProductsResponse productsResponse = new ProductsResponse(productList);
		try {
			json = JsonConverter.getJson(productsResponse);
		} catch (JsonProcessingException e) {
			System.out.println("[SainsburyScraper] Could not convert to Json! \n\n");
			e.printStackTrace();
		}
		return json;
	}
	
	public static void main(String[] args) {
		Validate.isTrue(args.length == 1, "usage: supply url to fetch");
		String json = scrapeUrl(args[0]);
		System.out.println(json);
	}
}
