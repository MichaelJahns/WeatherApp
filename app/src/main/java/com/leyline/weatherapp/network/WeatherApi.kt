package com.leyline.weatherapp.network

import com.leyline.weatherapp.repo.IWeatherNetwork

class WeatherApi(private val api: IWeatherNetwork) {
    fun getWeatherForCity(cityName: String, apiKey: String) = api.getWeatherForCity(cityName, apiKey)
}