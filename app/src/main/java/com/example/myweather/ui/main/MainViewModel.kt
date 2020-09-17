package com.example.myweather.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myweather.db.WeatherDatabase
import com.example.myweather.db.entity.Weather
import com.example.myweather.repo.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : WeatherRepository

    val weather : LiveData<List<Weather>>

    init {
        val weatherDao = WeatherDatabase.getDatabase(application, viewModelScope).weatherDao()
        repository = WeatherRepository(weatherDao)
        weather = repository.weather
    }

    fun insert(weather: Weather) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(weather)
    }
}
