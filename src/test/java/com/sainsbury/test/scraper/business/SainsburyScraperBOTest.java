package com.sainsbury.test.scraper.business;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import java.util.List;

import org.junit.Test;

import com.sainsbury.test.scraper.beans.Product;
import com.sainsbury.test.scraper.bussiness.SainsburyScraperBO;
public class SainsburyScraperBOTest {
	private final String SAINSBURY_TEST_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	private final String WRONG_TEST_URL = "http://google.com";
	private final String INVALID_URL = "bad.url";
	
	@Test
	public void SainsburyUrlReturnsProductList () {
		SainsburyScraperBO bo = new SainsburyScraperBO();
		List<Product> productList = bo.getProductList(SAINSBURY_TEST_URL);
		assertThat(productList, is(not(empty())));
	}
	
	@Test
	public void incorrectUrlReturnsEmptyProductList () {
		SainsburyScraperBO bo = new SainsburyScraperBO();
		List<Product> productList = bo.getProductList(WRONG_TEST_URL);
		assertThat(productList, is(empty()));
	}
	
	@Test(expected = Exception.class)
	public void invalidUrlThrowsException () {
		SainsburyScraperBO bo = new SainsburyScraperBO();
		bo.getProductList(INVALID_URL);
	}
	
}
