package com.nathan.digipizza;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PizzaListActivity extends AppCompatActivity {

//    private String[] pizzaNames = {"cheese", "hamburger", "meatlovers", "pepperoni", "supreme", "thin_crust"};
//
//    private String[] pizzaDescriptions = {"cheese_description", "hamburger_description", "meatlovers_description", "pepperoni_description", "supreme_desciption", "thin_crust_description"};
//
//    private int[] images = {R.drawable.cheese, R.drawable.hamburger, R.drawable.meatlovers, R.drawable.pepperoni, R.drawable.supreme, R.drawable.thin_crust};
//
//
//    private String[] pizzaPrices = {"$12.99", "$14.99", "$15.99", "$14.99", "$17.99", "regular price minus $2.00"};
//
//    private String[] pizzaOrderText = {"Order Now", "Order Now", "Order Now", "Order Now", "Order Now", "Order Now"};
//
//    private List<Pizza> mPizzaList = new ArrayList<>();
    

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.fragment_pizza_list);

        //Here is where you need to add PizzaListFragment (which includes the RecyclerView)
//        prepareTheList();
    }


//    private void prepareTheList ( ) {
//        int count = 0;
//
//        for (String name : pizzaNames) {
//
//            Pizza pizza = new Pizza(name, images[count] , pizzaDescriptions[count], pizzaPrices[count], pizzaOrderText[count]);
//            mPizzaList.add(pizza);
//            count++;
//        }
//    }

}
