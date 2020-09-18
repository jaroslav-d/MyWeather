package com.example.myweather.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myweather.db.dao.WeatherDao
import com.example.myweather.db.entity.Weather
import com.example.myweather.db.entity.WeatherUpdate
import com.example.myweather.service.AccuWeatherService
import com.example.myweather.service.models.CityInfo
import java.lang.Exception

class WeatherDBRepository(private val weatherDao: WeatherDao) {

    private val TAG : String = "WeatherDBRepository"

    private val mapOptionsConditions : Map<String, String> =
        mapOf(
            "apikey" to "SfFungljqxhY3vX5cVyeQGrOchjM2nkG",
            "language" to "ru"
        )
    private val mapOptionsSearch : MutableMap<String, String> =
        mutableMapOf(
            "apikey" to "SfFungljqxhY3vX5cVyeQGrOchjM2nkG",
            "language" to "ru"
        )
    val weather : LiveData<List<Weather>> = weatherDao.getWeather()
    private val service : AccuWeatherService = AccuWeatherService.create()

    suspend fun insert(weather : Weather) {
        weatherDao.insert(weather)
    }

    suspend fun getCities(text : String) : List<CityInfo> {
        mapOptionsSearch["q"] = text
        return service.searchCity(mapOptionsSearch)
    }

    suspend fun update(weather: WeatherUpdate) {
        weatherDao.update(weather)
    }

    suspend fun refreshData() {
        try {
            val keys = weatherDao.getKeys()
            for (key in keys) {
                val curCon = service.getCurrentCondition(key, mapOptionsConditions).first()
                val icon : String = curCon.weatherIcon
                val text : String = curCon.weatherText
                val temperature : Int = curCon.temperature.metric.value.toInt()
                update(WeatherUpdate(key,icon,text,temperature))
            }
        } catch (cause : Throwable) {
            Log.d(TAG,"Error")
        }
    }
}

//
//    fun refreshData() = withContext(Dispatchers.IO) {
//        for (key in keys) {
//            val curCon : Call<CurrentConditionCity> = service.getCurrentCondition(key, mapOptionsConditions)
//            curCon.enqueue(object : Callback<CurrentConditionCity> {
//                override fun onResponse(
//                    call: Call<CurrentConditionCity>,
//                    response: Response<CurrentConditionCity>
//                ) {
//                    val icon = response.body()!!.weatherIcon
//                    val text = response.body()!!.weatherText
//                    val temperature = response.body()!!.temperature.metric.value.toInt()
//                    GlobalScope.launch { update(WeatherUpdate(key,icon,text,temperature)) }
//                }
//
//                override fun onFailure(call: Call<CurrentConditionCity>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//            })
//        }