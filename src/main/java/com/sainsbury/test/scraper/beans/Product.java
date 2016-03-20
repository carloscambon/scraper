package com.sainsbury.test.scraper.beans;

public class Product {
    private String title;
    private String size;
    private float unit_price;
    private String description;

    
    public Product(String title, String size, float unit_price, String description) {
        super();
        this.title = title;
        this.size = size;
        this.unit_price = unit_price;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
