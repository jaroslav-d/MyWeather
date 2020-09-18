package com.example.myweather.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.db.WeatherDatabase
import com.example.myweather.repo.WeatherDBRepository
import kotlinx.coroutines.runBlocking

class SearchCityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : WeatherDBRepository

    init {
        val weatherDao = WeatherDatabase.getDatabase(application, viewModelScope).weatherDao()
        repository = WeatherDBRepository(weatherDao)
    }

    fun getCities(text : String) = runBlocking {
        repository.getCities(text)
    }

}