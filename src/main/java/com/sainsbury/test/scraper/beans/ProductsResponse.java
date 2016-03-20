package com.sainsbury.test.scraper.beans;

import java.util.List;

public class ProductsResponse  {

    public ProductsResponse(List<Product> results) {
        this.results = results;
        calculateTotal();
    }

    private void calculateTotal() {
        for (Product product : results) {
            total += product.getUnit_price();
        }
    }

    private List<Product> results;
    private float total = 0f;

    public float getTotal() {
        return total;
    }

    public List<Product> getResults() {
        return results;
    }
    
}
