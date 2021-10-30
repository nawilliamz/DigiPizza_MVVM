package com.nathan.digipizza;

import java.util.UUID;

public class Item {

    public UUID id;
    public String title;
    public String description;
    public String price;

    public Item(UUID id, String title, String description, String price) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.price = price;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
