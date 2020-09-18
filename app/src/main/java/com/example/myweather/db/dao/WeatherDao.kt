package com.example.myweather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myweather.db.entity.Weather
import com.example.myweather.db.entity.WeatherUpdate

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table")
    fun getWeather() : LiveData<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather : Weather)

    @Update(entity = Weather::class)
    suspend fun update(weatherUpdate: WeatherUpdate)

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()

    @Query("SELECT key FROM weather_table")
    suspend fun getKeys() : List<String>

}