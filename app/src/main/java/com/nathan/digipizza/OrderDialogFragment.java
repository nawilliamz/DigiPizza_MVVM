package com.nathan.digipizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.nathan.digipizza.databinding.OrderDialogLayoutBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDialogFragment extends AppCompatDialogFragment {


    OrderDialogLayoutBinding mOrderDialogLayoutBinding;
    MainViewModel mMainViewModel;
    UUID pizzaId;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {

                String result = bundle.getString("bundleKey");
                //Convert result to a UUID and then use result to find the correct Pizza, Pasta dish
                //by matching is from the list of Pizza, Pastas (may need to use a different REsultListener
                //for the Pastas)
                //Then use the dish to set a new Item and add it to the MutableLiveData<<List<Item>>
                //object in MainViewModel

                pizzaId = UUID.fromString(result);



//                List<Pizza> pizzaList = mOrderDialogLayoutBinding.getMainViewModel.getPizzas();
//                for (Pizza pizza: pizzaList) {
//                    if (pizza.getId().equals(pizzaId)) {
//                            UUID itemId = pizza.getId();
//                            String itemTitle = pizza.getName();
//                            String itemDescription = pizza.getDescription();
//                            String itemPrice = pizza.price;
//
//                            Item item = new Item(itemId, itemTitle, itemDescription, itemPrice);
//                            List<Item> itemList = new ArrayList<>();
//                            itemList.add(item);
//                            MainViewModel.getItems().setValue(itemList);
//                    }
//                }

            }
        });
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getActivity().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);


        mOrderDialogLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.order_dialog_layout, container, false);
        View view = mOrderDialogLayoutBinding.getRoot();

        return view;




//        mOrderDialogLayoutBinding = OrderDialogLayoutBinding.inflate(LayoutInflater.from(getContext()));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getActivity().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        mOrderDialogLayoutBinding.setVariable(mainViewModel, mMainViewModel);

        List<Pizza> pizzaList = mMainViewModel.getPizzas();
        for (Pizza pizza: pizzaList) {
            if (pizza.getId().equals(pizzaId)) {
                UUID itemId = pizza.getId();
                String itemTitle = pizza.getName();
                String itemDescription = pizza.getDescription();
                String itemPrice = pizza.price;

                Item item = new Item(itemId, itemTitle, itemDescription, itemPrice);
                List<Item> itemList = new ArrayList<>();
                itemList.add(item);
                mMainViewModel.getItems().setValue(itemList);
            }
        }
    }


}
