package com.nathan.digipizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.nathan.digipizza.databinding.SundaySpecialLayoutBinding;

public class SundaySpecialLayoutFragment extends Fragment {

    private SundaySpecialLayoutBinding mSundaySpecialLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mSundaySpecialLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.sunday_special_layout, container, false);

        View v = mSundaySpecialLayoutBinding.getRoot();

        return v;
    }
}
