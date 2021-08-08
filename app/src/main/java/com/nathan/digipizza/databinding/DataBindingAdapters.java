package com.nathan.digipizza.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class DataBindingAdapters {

    @BindingAdapter({"pizzaImage"})
    public static void setImageViewResource (ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter({"pastaImage"})
    public static void setPastaImageViewResource (ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
