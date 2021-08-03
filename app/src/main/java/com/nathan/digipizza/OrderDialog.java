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

import com.nathan.digipizza.databinding.OrderDialogLayoutBinding;

public class OrderDialog extends AppCompatDialogFragment {


    OrderDialogLayoutBinding mOrderDialogLayoutBinding;
    MainViewModel mMainViewModel;

    


        @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mOrderDialogLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.order_dialog_layout, container, false);
        View view = mOrderDialogLayoutBinding.getRoot();
        return view;

//        mOrderDialogLayoutBinding = OrderDialogLayoutBinding.inflate(LayoutInflater.from(getContext()));


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        mOrderDialogLayoutBinding.setVariable(mainViewModel, mMainViewModel);
    }


}
