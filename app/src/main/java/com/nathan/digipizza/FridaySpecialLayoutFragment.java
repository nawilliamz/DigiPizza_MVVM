package com.nathan.digipizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.nathan.digipizza.databinding.FridaySpecialLayoutBinding;

public class FridaySpecialLayoutFragment extends Fragment {

    private FridaySpecialLayoutBinding mFridaySpecialLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mFridaySpecialLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.friday_special_layout, container, false);

        View v = mFridaySpecialLayoutBinding.getRoot();

        return v;
    }

}
