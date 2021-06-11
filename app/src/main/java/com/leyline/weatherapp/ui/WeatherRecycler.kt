package com.leyline.weatherapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.leyline.weatherapp.R
import com.leyline.weatherapp.databinding.FragmentCityRecyclerBinding
import com.leyline.weatherapp.repo.models.Forecast
import com.leyline.weatherapp.viewmodel.Weather.WeatherViewModel


class WeatherRecycler(
    private val weatherViewModel: WeatherViewModel
) : Fragment(R.layout.fragment_city_recycler),
    WeatherRVAdapter.OnWeatherClickedListener {

    private lateinit var binding: FragmentCityRecyclerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityRecyclerBinding.inflate(layoutInflater, container, false)
        setUpObservers()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initializeUI()
        weatherViewModel.getForecasts().value?.let { updateUI(it) }
    }

    private fun setUpObservers() {
        weatherViewModel.getForecasts().observe(viewLifecycleOwner, Observer { forecasts ->
            updateUI(forecasts)
        })
    }

    private fun initializeUI() {
        (activity as AppCompatActivity).supportActionBar?.title = weatherViewModel.getCityName()
        binding.rvForecast.setHasFixedSize(true)
        binding.rvForecast.layoutManager = LinearLayoutManager(requireContext())
        updateUI(listOf<Forecast>())
    }

    private fun updateUI(forecastList: List<Forecast>) {
        val adapter = WeatherRVAdapter(this, forecastList)
        binding.rvForecast.adapter = adapter
    }

    override fun onWeatherClicked(forecast: Forecast) {
        weatherViewModel.setFocusedForecast(forecast)
    }

}