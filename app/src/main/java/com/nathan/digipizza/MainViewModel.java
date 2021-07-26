package com.nathan.digipizza;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {


    //LifeData objects can be used in MainViewModel if they need to be tracked by the the views
    //that reference them for changes. Here, the objects are static as they are just used to
    //fill in the Lists for informational purposes (to display pizza/pasta data for customers)

    private List<Pizza> pizzas;
    private List<Pasta> pastas;
    private List<Order> orders;

    public MainViewModel () {

        pizzas = new ArrayList<>();
        pastas = new ArrayList<>();
        orders = new ArrayList<>();

    }



    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Pasta> getPastas() {
        return pastas;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
