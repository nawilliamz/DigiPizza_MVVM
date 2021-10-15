package com.nathan.digipizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.nathan.digipizza.databinding.MondaySpecialLayoutBinding;

public class MondaySpecialLayoutFragment extends Fragment {

    private MondaySpecialLayoutBinding mMondaySpecialLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mMondaySpecialLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.monday_special_layout, container, false);

        View v = mMondaySpecialLayoutBinding.getRoot();
        return v;
    }
}
