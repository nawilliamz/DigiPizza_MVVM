package com.nathan.digipizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PastaListFragment extends Fragment {


    private RecyclerView mPastaRecyclerView;
    private PastaAdapter mPastaAdapter;

    private String[] pastaNames = {"Classic Italian Bake", "Classic Italian Pasta", "Classic Lasagna", "Classic Spaghetti", "Italian Shrimp Pasta", "Zesty Italian Pasta"};

    private String[] pastaDescriptions = {"classicitalianbake_description", "classicitalianpasta_description", "classiclasagna_description", "classicspaghetti_description", "italianshrimppasta_desciption", "zestyitalianpasta_description"};

    private int[] pastaImages = {R.drawable.classicitalianbake, R.drawable.classicitalianpasta, R.drawable.classiclasagna, R.drawable.classicspaghetti, R.drawable.italianshrimppasta, R.drawable.zestyitalianpasta};


    private String[] pastaPrices = {"$9.99", "$9.99", "$8.99", "$14.99", "$11.99", "12.99"};

    private String[] pastaOrderText = {"Order", "Order", "Order", "Order", "Order", "Order"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasta_list, container, false);

        //pasta_recycler_view is the id of the RecyclerView in fragment_pasta_list
        mPastaRecyclerView = (RecyclerView) view.findViewById(R.id.pasta_recycler_view);
        mPastaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

//        return super.onCreateView(inflater, container, savedInstanceState)
        return view;
    }


    private class PastaHolder extends RecyclerView.ViewHolder {


        //The ViewHolder constructor must take in an View argument type
        //To do this however, we will inflate our fragment_pizza_layout.xml file and pass this
        //into the super constructor. Thus, the base ViewHolder class will hold on to the
        //fragment_pizza_layout view heirarchy. If you need that view heirarchy, you can find it
        //it in ViewHolder's itemView field

        public TextView pastaName;
        public ImageView pastaImage;
        public TextView pastaDescription;
        public TextView pastaPrice;
        public Button pastaOrder;

        public PastaHolder(@NonNull View itemView) {
            super(itemView);

            pastaName = itemView.findViewById(R.id.pasta_name);
            pastaImage = itemView.findViewById(R.id.pasta_image);
            pastaDescription = itemView.findViewById(R.id.pasta_description);
            pastaPrice = itemView.findViewById(R.id.pasta_price);
            pastaOrder = itemView.findViewById(R.id.pasta_order_button);

        }
    }

    private class PastaAdapter extends RecyclerView.Adapter<PastaListFragment.PastaHolder> {

        private List<Pasta> mPastas;

        public PastaAdapter (List<Pasta> pastas) {
            mPastas = pastas;
        }

        //We're doing the same thing with onCreateViewHolder here as we did with PizzaHolder
        //constructor. The method requires a

        @NonNull
        @Override
        public PastaListFragment.PastaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            //LayoutInflater.from(context) obtains the layout inflater from the given Context
            //Note: I'm still not sure which Activity will be the parent of this fragment--MainActivity or PizzaListActivity
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_pasta_layout, parent, false);
            return new PastaHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PastaListFragment.PastaHolder holder, int position) {

            Pasta pasta = mPastas.get(position);
            holder.pastaName.setText(pasta.getName());
            holder.pastaImage.setImageResource(pasta.getPastaImage());
            holder.pastaDescription.setText(pasta.getDescription());
            holder.pastaPrice.setText(pasta.getPrice());
            holder.pastaOrder.setText(pasta.getOrderText());

        }

        @Override
        public int getItemCount() {
            return mPastas.size();
        }
    }

    private void updateUI() {

        //Retrieve your singleton
        CentralizedStorage centralizedStorage = CentralizedStorage.get(getActivity());
        List<Pasta> pastas = centralizedStorage.getPastas();
        prepareTheList(pastas);

        mPastaAdapter = new PastaListFragment.PastaAdapter(pastas);
        mPastaRecyclerView.setAdapter(mPastaAdapter);

    }

    private void prepareTheList (List<Pasta> pastas ) {
        int count = 0;

        for (String name : pastaNames) {

            Pasta pasta = new Pasta(name, pastaImages[count] , pastaDescriptions[count], pastaPrices[count], pastaOrderText[count]);
            pastas.add(pasta);
            count++;
        }
    }
}
