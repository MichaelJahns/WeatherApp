package com.leyline.weatherapp.repo.models

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)