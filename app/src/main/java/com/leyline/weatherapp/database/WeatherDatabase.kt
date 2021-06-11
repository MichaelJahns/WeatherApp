package com.leyline.weatherapp.database

import com.leyline.weatherapp.repo.WeatherDao

class WeatherDatabase private constructor() {
    var weatherDao = WeatherDao()
        private set

    companion object{
        @Volatile
        private var INSTANCE: WeatherDatabase ?= null
        fun getInstance() =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: WeatherDatabase().also {
                    INSTANCE = it
                }
            }
    }
}