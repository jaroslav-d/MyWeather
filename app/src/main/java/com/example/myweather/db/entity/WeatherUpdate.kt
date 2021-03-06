package com.example.myweather.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherUpdate (
    @PrimaryKey
    @ColumnInfo(name = "key")
    val key : String,
    @ColumnInfo(name = "icon")
    val icon : String,
    @ColumnInfo(name = "text")
    val text : String,
    @ColumnInfo(name = "temperature")
    val temperature : Int
)