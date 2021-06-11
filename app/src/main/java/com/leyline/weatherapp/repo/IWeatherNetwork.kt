package com.leyline.weatherapp.repo

import com.leyline.weatherapp.BuildConfig
import com.leyline.weatherapp.repo.models.WeatherForecast
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherNetwork {

    @GET("/data/2.5/forecast")
    fun getWeatherForCity(
        @Query("q")
        cityName: String,
        @Query("appid")
        apiKey: String,
        @Query("units")
        units: String = "metric",
        @Query("lang")
        lang: String = "en"
    ) : Call<WeatherForecast>
}