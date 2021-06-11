package com.leyline.weatherapp.ui

import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.leyline.weatherapp.R
import com.leyline.weatherapp.databinding.FragmentCityLookupBinding
import com.leyline.weatherapp.viewmodel.Weather.WeatherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CityLookup(
    private var weatherViewModel: WeatherViewModel
    ) : Fragment(R.layout.fragment_city_lookup) {
    private lateinit var binding: FragmentCityLookupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityLookupBinding.inflate(layoutInflater, container, false)
        initUI()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initUI()
    }

    fun initUI(){
        (activity as AppCompatActivity).supportActionBar?.title = "Weather App"
        binding.lookupCity.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                weatherViewModel.getForecastsFor(binding.etCityName.text.toString())
            }
            weatherViewModel.setCityString(binding.etCityName.text.toString())
        }
    }
}