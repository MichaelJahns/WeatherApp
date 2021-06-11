package com.leyline.weatherapp.repo.models

data class WeatherForecast(
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)