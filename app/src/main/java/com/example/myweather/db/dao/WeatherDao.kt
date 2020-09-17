package com.example.myweather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweather.db.entity.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table")
    fun getWeather() : LiveData<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather : Weather)

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()

}