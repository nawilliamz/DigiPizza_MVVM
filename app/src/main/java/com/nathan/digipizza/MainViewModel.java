package com.nathan.digipizza;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {


    //LiveData objects can be used in MainViewModel if they need to be tracked by the the views
    //that reference them for changes. Here, the objects are static as they are just used to
    //fill in the Lists for informational purposes (to display pizza/pasta data for customers)

    OrderDialogFragment dialog = new OrderDialogFragment();

    private List<Pizza> pizzas;
    private List<Pasta> pastas;
    private List<Salad> salads;
    private List<Customer> customers;
    private MutableLiveData<List<Item>> items;
    private MutableLiveData<List<Order>> orders;



    public MainViewModel () {

        pizzas = new ArrayList<>();
        pastas = new ArrayList<>();
        salads = new ArrayList<>();
        customers = new ArrayList<>();
        items = new MutableLiveData<List<Item>>();
        orders = new MutableLiveData<List<Order>>();


    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Pasta> getPastas() {
        return pastas;
    }

    public MutableLiveData<List<Order>> getOrders() {
        return orders;
    }

    public List<Salad> getSalads() {
        return salads;
    }

    public MutableLiveData<List<Item>> getItems() {
        return items;
    }

    public String calculatePrice () {
        List<Item> list = items.getValue();
        double totalPrice = 0.0;

        if (list !=  null) {
            for (Item item : list) {
                double itemPrice = Double.parseDouble(item.price);
                totalPrice = totalPrice += itemPrice;

            }
        };

        return Double.toString(totalPrice);

    }

    public String getNumberOfItems () {
        int itemsSize = items.getValue().size();
        return Integer.toString(itemsSize);
    }
}
