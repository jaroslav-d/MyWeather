package com.example.myweather.service.models

import com.google.gson.annotations.SerializedName

data class CityInfo(
    @SerializedName("Key")
    val key : String,
    @SerializedName("LocalizedName")
    val name : String,
    @SerializedName("Country")
    val country : Country,
    @SerializedName("AdministrativeArea")
    val administrativeArea : AdministrativeArea
) {
    data class Country(
        @SerializedName("LocalizedName")
        val localizedName : String
    )
    data class AdministrativeArea(
        @SerializedName("LocalizedName")
        val localizedName : String
    )
}