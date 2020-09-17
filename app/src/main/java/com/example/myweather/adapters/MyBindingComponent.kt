package com.example.myweather.adapters

import androidx.databinding.DataBindingComponent

class MyBindingComponent() : DataBindingComponent {

    override fun getMyBindingAdapter(): MyBindingAdapter {
        return MyBindingAdapter()
    }
}