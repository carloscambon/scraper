package com.sainsbury.test.scraper;

import java.io.IOException;
import java.util.List;

import org.jsoup.helper.Validate;

import com.sainsbury.test.scraper.beans.Product;
import com.sainsbury.test.scraper.beans.ProductsResponse;
import com.sainsbury.test.scraper.utils.HtmlParser;
import com.sainsbury.test.scraper.utils.JsonConverter;

public class SainsburyScraper {

    public static void main(String[] args) {
        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = args[0];
        System.out.printf("Fetching %s...", url);
        try {
            List<Product> productList = HtmlParser.getProductList(url);
    		ProductsResponse productsResponse = new ProductsResponse(productList);
    		String json = JsonConverter.getJson(productsResponse);
    		System.out.println(json);
        } catch (IOException e) {
        	System.out.println("Exception when parsing site provided. Exiting...\n\n");
        	e.printStackTrace();
        }

    }

}
