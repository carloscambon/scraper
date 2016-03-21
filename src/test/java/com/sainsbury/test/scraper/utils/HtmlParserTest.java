package com.sainsbury.test.scraper.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class HtmlParserTest {
	private final String SAINSBURY_TEST_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	private final String INVALID_URL = "bad.url";

	@Test
	public void documentIsReturnedOnCorrectUrl() throws IOException {
		Document doc = HtmlParser.getDocumentFromUrl(SAINSBURY_TEST_URL);
		assertThat(doc, is(not(nullValue())));
	}

	@Test(expected = Exception.class)
	public void exceptionIsThrownOnInvalidUrl() throws IOException {
		HtmlParser.getDocumentFromUrl(INVALID_URL);
	}

	@Test
	public void webPageSizeIsReturnedForCorrectUrl() throws IOException {
		String size = HtmlParser.getWebPageSize(SAINSBURY_TEST_URL);
		assertThat(size, not(isEmptyOrNullString()));
	}

	@Test(expected = Exception.class)
	public void webPageSizeThowsExceptionOnInvalidUrl() throws IOException {
		HtmlParser.getWebPageSize(INVALID_URL);
	}
}
