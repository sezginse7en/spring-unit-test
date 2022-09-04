package com.shopping.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("productitem")
public class ProductItem implements Cloneable {

    @Id
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    private String title;
    private String model;
    private Double price;

    public ProductItem(String title, String model, Double price) {
        super();
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.model = model;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }


}
