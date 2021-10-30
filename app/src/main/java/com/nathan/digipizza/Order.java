package com.nathan.digipizza;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "orders")
public class Order {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "orderId")
    private UUID orderId;

    @ColumnInfo(name = "customerFirstName")
    private String customerFirstName;

    @ColumnInfo(name = "customerLastName")
    private String customerLastName;

    @ColumnInfo(name = "customerPhone")
    private String customerPhone;

    @ColumnInfo(name = "numberOfItems")
    private int numberOfItems;

    @ColumnInfo(name = "totalPrice")
    private String totalPrice;



    public Order(UUID orderId) {


        this.orderId = orderId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerPhone = customerPhone;
        this.numberOfItems = numberOfItems;
        this.totalPrice = totalPrice;
    }

    public Order() {
        this(UUID.randomUUID());
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }


    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setCustomerName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
