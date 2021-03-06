package com.example.myweather.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class Weather(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "key")
    val key : String,
    @ColumnInfo(name = "icon")
    val icon : String,
    @NonNull
    @ColumnInfo(name = "city")
    val city : String,
    @ColumnInfo(name = "text")
    val text : String,
    @ColumnInfo(name = "temperature")
    val temperature : Int
)