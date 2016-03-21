package com.sainsbury.test.scraper;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sainsbury.test.scraper.beans.ProductsResponse;
import com.sainsbury.test.scraper.utils.JsonConverter;

public class SainsburyScraperTest {
		private final String SAINSBURY_TEST_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

		@Test
		public void scraperResultContainsTotal () throws JsonParseException, JsonMappingException, IOException {
			String json = SainsburyScraper.scrapeUrl(SAINSBURY_TEST_URL);
			ProductsResponse productsResponse = (ProductsResponse) JsonConverter.getObject(json);
			org.junit.Assert.assertTrue("Total should be more than 0", productsResponse.getTotal()>0);
		}
		
		@Test
		public void scraperResultContainsProducts () throws JsonParseException, JsonMappingException, IOException {
			String json = SainsburyScraper.scrapeUrl(SAINSBURY_TEST_URL);
			ProductsResponse productsResponse = (ProductsResponse) JsonConverter.getObject(json);
			org.junit.Assert.assertTrue("Products should be returned", productsResponse.getResults().size()>0);
		}
		
		@Test
		public void scraperResultReturnsSizeOfPage () throws JsonParseException, JsonMappingException, IOException {
			String json = SainsburyScraper.scrapeUrl(SAINSBURY_TEST_URL);
			ProductsResponse productsResponse = (ProductsResponse) JsonConverter.getObject(json);
			org.junit.Assert.assertTrue("Web page size should be returned", productsResponse.getResults().get(0).getSize().contains("kb"));
		}
		
		@Test
		public void scraperResultReturnsProductTitle () throws JsonParseException, JsonMappingException, IOException {
			String json = SainsburyScraper.scrapeUrl(SAINSBURY_TEST_URL);
			ProductsResponse productsResponse = (ProductsResponse) JsonConverter.getObject(json);
			org.junit.Assert.assertTrue("Product title should be returned", productsResponse.getResults().get(0).getTitle().length()>0);
		}
		
		@Test
		public void scraperResultReturnsProductUnitPrice () throws JsonParseException, JsonMappingException, IOException  {
			String json = SainsburyScraper.scrapeUrl(SAINSBURY_TEST_URL);
			ProductsResponse productsResponse = (ProductsResponse) JsonConverter.getObject(json);
			org.junit.Assert.assertTrue("Product unit_price should be returned", productsResponse.getResults().get(0).getUnit_price()>0f);
		}
		
		@Test
		public void scraperResultReturnsProductDescription () throws JsonParseException, JsonMappingException, IOException  {
			String json = SainsburyScraper.scrapeUrl(SAINSBURY_TEST_URL);
			ProductsResponse productsResponse = (ProductsResponse) JsonConverter.getObject(json);
			org.junit.Assert.assertTrue("Product description should be returned", productsResponse.getResults().get(0).getUnit_price()>0f);
		}

}
