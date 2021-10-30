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

import com.nathan.digipizza.databinding.OrderSummaryLayoutBinding;

public class OrderSummaryDialogFragment extends AppCompatDialogFragment  {

    OrderSummaryLayoutBinding mOrderSummaryLayoutBinding;
    MainViewModel mMainViewModel;
    String orderIdLastFive;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //Retrieve the item ID when user clicks on Order button (could be either a Pizza or Pasta item)
        //It appears both Pizza list fragment and Pasta list fragment are both managed by the same Fragment Manager

        //Here I need to retrieve the Order ID from OrderDialogFragment
        getParentFragmentManager().setFragmentResultListener("orderKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {

                String result = bundle.getString("orderId");

                //I'm only going to display the last five characters of the order Id
                orderIdLastFive = result.substring(result.length() - 5);
            }
        });

        mOrderSummaryLayoutBinding.setOrderId(orderIdLastFive);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mOrderSummaryLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.order_summary_layout, container, false);
        View view = mOrderSummaryLayoutBinding.getRoot();



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

//        mOrderSummaryLayoutBinding.setOrderId(orderIdLastFive);

    }



}
