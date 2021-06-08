package com.nathan.digipizza;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CentralizedStorage {

    //Intantiates our centralized storage singleton
    private static CentralizedStorage sCentralizedStorage;

    private static Pizza mPizza;
    private List<Pizza> mPizzas;

    private static Pasta mPasta;
    private List<Pasta> mPastas;

    //Remember, a singleton exists as long as the application stays in memory, so storing the Piazz
    //list in a singleton will keep the crime data available throughout any lifecycled changes
    //in  your activities and fragments
    private CentralizedStorage(Context context) {

        //Setting a temporary list of Pizza to use for development
        mPizzas = new ArrayList<>();
        mPastas = new ArrayList<>();
//        for (int i = 0; i < 12; i++) {
//            Pizza pizza = new Pizza();
//            pizza.setName("Pizza #" + i);
//            mPizzas.add(pizza);
//
//        }
    }

    public void addPizza(Pizza pizza) {
        mPizzas.add(pizza);
    }

    public void addPasta(Pasta pasta) {
        mPastas.add(pasta);
    }

    public Pizza getPizza(UUID id) {
        for (Pizza pizza : mPizzas) {
            if (pizza.getId().equals(id)) {
                return pizza;
            }
        }
        return null;
    }

    public Pasta getPasta(UUID id) {
        for (Pasta pasta: mPastas) {
            if (pasta.getId().equals(id)) {
                return pasta;
            }
        }
        return null;
    }


    //Allows access to the Centralized Storage singleton in other activities
    public static CentralizedStorage get (Context context) {
        if (sCentralizedStorage == null) {
            sCentralizedStorage = new CentralizedStorage(context);
        }
        return sCentralizedStorage;
    }

    public List<Pizza> getPizzas() {
        return mPizzas;
    }

    public List<Pasta> getPastas() {
        return mPastas;
    }
}
