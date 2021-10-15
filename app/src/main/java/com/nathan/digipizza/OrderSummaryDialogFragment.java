package com.nathan.digipizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.nathan.digipizza.databinding.OrderSummaryLayoutBinding;

public class OrderSummaryDialogFragment extends AppCompatDialogFragment {

    OrderSummaryLayoutBinding mOrderSummaryLayoutBinding;
    MainViewModel mMainViewModel;


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
    }
}
