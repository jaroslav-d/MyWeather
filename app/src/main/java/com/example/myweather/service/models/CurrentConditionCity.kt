package com.example.myweather.service.models

import com.google.gson.annotations.SerializedName


data class CurrentConditionCity(
    @SerializedName("WeatherText")
    val weatherText : String,
    @SerializedName("WeatherIcon")
    val weatherIcon : String,
    @SerializedName("Temperature")
    val temperature : Temperature
) {
    data class Temperature(
        @SerializedName("Metric")
        val metric : Metric
    ) {
        data class Metric(
            @SerializedName("Value")
            val value : Float
        )
    }
}