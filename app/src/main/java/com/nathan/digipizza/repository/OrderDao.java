package com.nathan.digipizza.repository;


import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nathan.digipizza.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void insertOrder (Order order);


    @Query("SELECT * FROM orders")
    MutableLiveData<List<Order>> getAllOrders();

}
