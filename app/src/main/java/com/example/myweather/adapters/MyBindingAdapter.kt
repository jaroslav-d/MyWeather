package com.example.myweather.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class MyBindingAdapter {

    @BindingAdapter("icon_web")
    fun loadImage(view : ImageView, icon : String) = Picasso.get().load(buildUrlFromIcon(icon)).into(view)

    private fun buildUrlFromIcon(icon : String) = "https://developer.accuweather.com/sites/default/files/$icon-s.png"

}