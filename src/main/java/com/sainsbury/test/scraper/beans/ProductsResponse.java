package com.sainsbury.test.scraper.beans;

import java.util.List;

/**
 * Bean to hold the Sainsbury product list and the total sum of the unit_price
 * of each product on the list
 * 
 * @author Carlos Cambon
 *
 */
public class ProductsResponse {
	private List<Product> results;
	private float total = 0f;

	public ProductsResponse() {

	}

	public ProductsResponse(List<Product> results) {
		this.results = results;
		calculateTotal();
	}

	private void calculateTotal() {
		for (Product product : results) {
			total += product.getUnit_price();
		}
	}

	public float getTotal() {
		return total;
	}

	public List<Product> getResults() {
		return results;
	}

}
