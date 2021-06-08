package com.nathan.digipizza;

import java.util.UUID;

public class Pasta {

    UUID id;
    String name;
    int pastaImage;
    String description;
    String price;
    String orderText;

    public Pasta(String name, int pastaImage, String description, String price, String orderText) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.pastaImage = pastaImage;
        this.description = description;
        this.price = price;
        this.orderText = orderText;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPastaImage() {
        return pastaImage;
    }

    public void setPastaImage(int pastaImage) {
        this.pastaImage = pastaImage;
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

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }
}
