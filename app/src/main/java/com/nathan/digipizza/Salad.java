package com.nathan.digipizza;

public class Salad {

    private String name;
    private String description;

    public Salad(String name, String description) {

        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name + "\n\n" + description + "\n\n";
    }
}
