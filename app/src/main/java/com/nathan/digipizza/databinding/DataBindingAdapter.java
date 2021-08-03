package com.nathan.digipizza.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class DataBindingAdapter {

    @BindingAdapter({"pizzaImage"})
    public static void setImageViewResource (ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
