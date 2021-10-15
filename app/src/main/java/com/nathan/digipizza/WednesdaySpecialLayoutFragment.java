package com.nathan.digipizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.nathan.digipizza.databinding.WednesdaySpecialLayoutBinding;

public class WednesdaySpecialLayoutFragment extends Fragment {

    private WednesdaySpecialLayoutBinding mWednesdaySpecialLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mWednesdaySpecialLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.wednesday_special_layout, container, false);

        View v = mWednesdaySpecialLayoutBinding.getRoot();

        return v;
    }
}
