package com.nathan.digipizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.nathan.digipizza.databinding.TuesdaySpecialLayoutBinding;

public class TuesdaySpecialLayoutFragment extends Fragment {

    private TuesdaySpecialLayoutBinding mTuesdaySpecialLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mTuesdaySpecialLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.tuesday_special_layout, container, false);

        View v = mTuesdaySpecialLayoutBinding.getRoot();

        return v;
    }
}
