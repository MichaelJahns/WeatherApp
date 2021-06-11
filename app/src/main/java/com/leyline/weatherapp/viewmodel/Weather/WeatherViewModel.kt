package com.leyline.weatherapp.viewmodel.Weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.leyline.weatherapp.repo.models.Forecast
import com.leyline.weatherapp.repo.models.WeatherForecast
import com.leyline.weatherapp.repo.WeatherRepository

class WeatherViewModel(
    private var weatherRepo: WeatherRepository
) : ViewModel() {
    fun getCity(): LiveData<WeatherForecast> {
        return weatherRepo.getCity()
    }

    fun getForecasts(): LiveData<List<Forecast>> {
        return weatherRepo.getForecasts()
    }

    fun getFocusedForecast(): LiveData<Forecast>{
        return weatherRepo.getFocusedForecast()
    }

    suspend fun getForecastsFor(cityName: String) = weatherRepo.getForecastReportFor(cityName)

    fun setFocusedForecast(forecast: Forecast) {
        weatherRepo.setFocusedForecast(forecast)
    }

    fun getCityName(): String {
        return weatherRepo.getCityName()
    }
    fun setCityString(cityName: String) {
        weatherRepo.setCityName(cityName)
    }
}