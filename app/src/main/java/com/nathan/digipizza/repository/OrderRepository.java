package com.nathan.digipizza.repository;

import android.app.Application;

import com.nathan.digipizza.Order;

import java.util.List;

public class OrderRepository {

    private static OrderRepository instance;
    private List<Order> orders;
    private final OrderDao orderDao;

    private OrderRepository(Application application) {
        OrderRoomDatabase db;
        db = OrderRoomDatabase.getDatabase(application);
        orderDao = db.orderDao();


    }

    public static OrderRepository getInstance(Application application) {

        if (instance == null) {
            instance = new OrderRepository(application);
        }
        return instance;
    }


    //This idea with the methods below is to retrieve all of the current Orders from Room
    //, add them to your Orders list and then set that list as a MutableLiveData object
//    public LiveData<List<Order>> getOrderList() {
//        MutableLiveData<List<Order>> orderList = new MutableLiveData<>();
//        List<Order> orders = setOrders();
//
//        //the setValue() method sets orders as your List of MutableLiveData
//        orderList.setValue(orders);
//        return orderList;
//    }

    //This method is meant to mimic retrieval of the orders from our DB
    //This is where you will place method for accessing Room DB
    //Here, we want to add our Orders retrieved from the DB to our ArrayList
//    private List<Order> setOrders () {
//
//    }
}
