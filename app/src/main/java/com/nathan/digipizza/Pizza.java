package com.nathan.digipizza;

import java.util.UUID;


public class Pizza {


        UUID id;
        String name;
        int pizzaImage;
        String description;
        String price;
        String orderText;

    public Pizza(String name, int pizzaImage, String description, String price, String orderText) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.pizzaImage = pizzaImage;
        this.description = description;
        this.price = price;
        this.orderText = orderText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public int getPizzaImage() {
        return pizzaImage;
    }

    public void setPizzaImage(int pizzaImage) {
        this.pizzaImage = pizzaImage;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }
}
