package com.nathan.digipizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class PizzaListFragment extends Fragment {

    private RecyclerView mPizzaRecyclerView;
    private PizzaAdapter mAdapter;

    private String[] pizzaNames = {"Cheese", "Hamburger", "Meatlovers", "Pepperoni", "Supreme", "Thin_crust"};

    private String[] pizzaDescriptions = {"cheese_description", "hamburger_description", "meatlovers_description", "pepperoni_description", "supreme_desciption", "thin_crust_description"};

    private int[] images = {R.drawable.cheese, R.drawable.hamburger, R.drawable.meatlovers, R.drawable.pepperoni, R.drawable.supreme, R.drawable.thin_crust};


    private String[] pizzaPrices = {"$12.99", "$14.99", "$15.99", "$14.99", "$17.99", "regular price minus $2.00"};

    private String[] pizzaOrderText = {"Order", "Order", "Order", "Order", "Order", "Order"};



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza_list, container, false);

        //pizza_recycler_view is the id of the RecyclerView in fragment_pizza_list
        mPizzaRecyclerView = (RecyclerView) view.findViewById(R.id.pizza_recycler_view);
        mPizzaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private class PizzaHolder extends RecyclerView.ViewHolder {


        //The ViewHolder constructor must take in an View argument type
        //To do this however, we will inflate our fragment_pizza_layout.xml file and pass this
        //into the super constructor. Thus, the base ViewHolder class will hold on to the
        //fragment_pizza_layout view heirarchy. If you need that view heirarchy, you can find it
        //it in ViewHolder's itemView field

        public TextView pizzaName;
        public ImageView pizzaImage;
        public TextView pizzaDescription;
        public TextView pizzaPrice;
        public TextView pizzaOrder;

        public PizzaHolder(@NonNull View itemView) {
            super(itemView);

            pizzaName = itemView.findViewById(R.id.pizza_name);
            pizzaImage = itemView.findViewById(R.id.pizza_image);
            pizzaDescription = itemView.findViewById(R.id.pizza_description);
            pizzaPrice = itemView.findViewById(R.id.pizza_price);
            pizzaOrder = itemView.findViewById(R.id.rounded_button);

        }
    }

    private class PizzaAdapter extends RecyclerView.Adapter<PizzaHolder> {

        private List<Pizza> mPizzas;

        public PizzaAdapter (List<Pizza> pizzas) {
            mPizzas = pizzas;
        }

        //We're doing the same thing with onCreateViewHolder here as we did with PizzaHolder
        //constructor. The method requires a

        @NonNull
        @Override
        public PizzaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            //LayoutInflater.from(context) obtains the layout inflater from the given Context
            //Note: I'm still not sure which Activity will be the parent of this fragment--MainActivity or PizzaListActivity
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_pizza_layout, parent, false);
            return new PizzaHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PizzaHolder holder, int position) {

            Pizza pizza = mPizzas.get(position);
            holder.pizzaName.setText(pizza.getName());
            holder.pizzaImage.setImageResource(pizza.getPizzaImage());
            holder.pizzaDescription.setText(pizza.getDescription());
            holder.pizzaPrice.setText(pizza.getPrice());
            holder.pizzaOrder.setText(pizza.getOrderText());

        }

        @Override
        public int getItemCount() {
            return mPizzas.size();
        }
    }

    private void updateUI() {

        //Retrieve your singleton
        CentralizedStorage centralizedStorage = CentralizedStorage.get(getActivity());
        List<Pizza> pizzas = centralizedStorage.getPizzas();
        prepareTheList(pizzas);

        mAdapter = new PizzaAdapter(pizzas);
        mPizzaRecyclerView.setAdapter(mAdapter);

    }

    private void prepareTheList (List<Pizza> pizzas ) {
        int count = 0;

        for (String name : pizzaNames) {

            Pizza pizza = new Pizza(name, images[count] , pizzaDescriptions[count], pizzaPrices[count], pizzaOrderText[count]);
            pizzas.add(pizza);
            count++;
        }
    }


}
