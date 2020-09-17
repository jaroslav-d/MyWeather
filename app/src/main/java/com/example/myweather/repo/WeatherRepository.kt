package com.example.myweather.repo

import androidx.lifecycle.LiveData
import com.example.myweather.db.dao.WeatherDao
import com.example.myweather.db.entity.Weather

class WeatherRepository(private val weatherDao: WeatherDao) {

    val weather : LiveData<List<Weather>> = weatherDao.getWeather()

    suspend fun insert(weather : Weather) {
        weatherDao.insert(weather)
    }
}