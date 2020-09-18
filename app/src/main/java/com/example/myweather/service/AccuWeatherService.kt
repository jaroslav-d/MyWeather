package com.example.myweather.service

import androidx.lifecycle.LiveData
import com.example.myweather.service.models.CityInfo
import com.example.myweather.service.models.CurrentConditionCity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AccuWeatherService {

    @GET("currentconditions/v1/{key_city}")
    suspend fun getCurrentCondition(@Path("key_city") key : String, @QueryMap options: Map<String,String>)
            : List<CurrentConditionCity>

    @GET("locations/v1/cities/search")
    suspend fun searchCity(@QueryMap options: Map<String, String>) : List<CityInfo>

    companion object Factory {
        fun create(): AccuWeatherService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(AccuWeatherService::class.java)
        }
    }
}