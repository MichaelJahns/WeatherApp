package com.leyline.weatherapp.viewmodel.Weather

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leyline.weatherapp.network.WeatherApi
import com.leyline.weatherapp.repo.WeatherRepository

class WeatherViewModelFactory(
    private val weatherApi: WeatherApi,
    private val applicationContext: Context
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(WeatherRepository(weatherApi, applicationContext)) as T
    }

}