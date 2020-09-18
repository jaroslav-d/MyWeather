package com.example.myweather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myweather.db.dao.WeatherDao
import com.example.myweather.db.entity.Weather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao() : WeatherDao

    private class WeatherDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var weatherDao = database.weatherDao()

                    weatherDao.deleteAll()

                    var weather = Weather(
                        "1-2501755_1_AL",
                        "34",
                        "Набережные Челны",
                        "Преимущественно ясно",
                        1
                    )
                    weatherDao.insert(weather)
                    weather = Weather(
                        "290833",
                        "36",
                        "Елабуга",
                        "Небольшая облачность",
                        2
                    )
                    weatherDao.insert(weather)
                    weather = Weather(
                        "295954",
                        "38",
                        "Казань",
                        "Преимущественно облачно",
                        3
                    )
                    weatherDao.insert(weather)
                }
            }

        }
    }

    companion object {

        @Volatile
        private var INSTANCE : WeatherDatabase? = null

        fun getDatabase(context : Context, scope: CoroutineScope) : WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                )
                    .addCallback(WeatherDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}