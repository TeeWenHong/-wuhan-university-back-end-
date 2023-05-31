package com.qingshuge.bean;

import org.springframework.data.elasticsearch.annotations.Document;

//@Document(indexName = "products",createIndex = true)
public class Product {

    private String title;

    private Double price;

    private String description;

    public Product(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product(String title, Double price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
