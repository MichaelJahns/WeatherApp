package com.leyline.weatherapp.viewmodel.Weather

import androidx.lifecycle.LiveData
import com.leyline.weatherapp.repo.models.Forecast
import com.leyline.weatherapp.repo.models.WeatherForecast

interface IWeatherRepo {
    fun getCity() : LiveData<WeatherForecast>
    fun getForecasts(): LiveData<List<Forecast>>
    fun getFocusedForecast(): LiveData<Forecast>
    suspend fun getForecastReportFor(cityName: String)
    fun setFocusedForecast(forecast: Forecast)
    fun setCityName(cityName: String)
    fun getCityName(): String
}