package com.nathan.digipizza.repository;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nathan.digipizza.Order;

@Database(entities = {Order.class}, version = 1)
public abstract class OrderRoomDatabase extends RoomDatabase {


    private static OrderRoomDatabase INSTANCE;

    static OrderRoomDatabase getDatabase (final Context context) {

        if (INSTANCE == null) {
            synchronized (OrderRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    OrderRoomDatabase.class, "order_database").build();
                }

            }
        }

        return INSTANCE;
    }

    public abstract OrderDao orderDao();

}
