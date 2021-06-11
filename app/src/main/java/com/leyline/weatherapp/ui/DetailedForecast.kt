package com.leyline.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.leyline.weatherapp.R
import com.leyline.weatherapp.databinding.FragmentDetailedForecastBinding
import com.leyline.weatherapp.repo.models.Forecast
import com.leyline.weatherapp.viewmodel.Weather.WeatherViewModel


class DetailedForecast(
    private var weatherViewModel: WeatherViewModel
) : Fragment(R.layout.fragment_detailed_forecast) {
    private lateinit var binding: FragmentDetailedForecastBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailedForecastBinding.inflate(layoutInflater, container, false)
        setUpObservers()
        return binding.root
    }

    fun setUpObservers(){
        weatherViewModel.getFocusedForecast().observe(viewLifecycleOwner, Observer { forecast ->
            updateUI(forecast)
        })
    }

    fun updateUI(forecast: Forecast){
        val temperatureString = forecast.main.temp.toString() + " C"
        val feelsLikeString = "Feels Like : " + forecast.main.feels_like.toString() + " C"
        binding.tvRealTemperature.text = temperatureString
        binding.tvFeelsLikeTemperature.text = feelsLikeString
        binding.tvWeatherSystem.text = forecast.weather[0].main
        binding.tvWeatherDescription.text = forecast.weather[0].description
    }
}