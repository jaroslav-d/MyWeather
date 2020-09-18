package com.example.myweather.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myweather.db.WeatherDatabase
import com.example.myweather.db.entity.Weather
import com.example.myweather.repo.WeatherDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : WeatherDBRepository

    val weather : LiveData<List<Weather>>

    init {
        val weatherDao = WeatherDatabase.getDatabase(application, viewModelScope).weatherDao()
        repository = WeatherDBRepository(weatherDao)
        refreshData()
        weather = repository.weather
    }

    fun refreshData() = viewModelScope.launch {
        repository.refreshData()
    }

    fun insert(weather: Weather) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(weather)
    }
}
