package com.leyline.weatherapp.ui

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.leyline.weatherapp.R
import com.leyline.weatherapp.network.RetrofitInstance
import com.leyline.weatherapp.network.WeatherApi
import com.leyline.weatherapp.viewmodel.Weather.WeatherViewModel
import com.leyline.weatherapp.viewmodel.Weather.WeatherViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var cityLookupFragment: CityLookup
    private lateinit var recyclerFragment: WeatherRecycler
    private lateinit var detailedForecastFragment: DetailedForecast

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherViewModelFactory: WeatherViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherViewModelFactory = WeatherViewModelFactory(
            WeatherApi(RetrofitInstance.weatherApi),
            this
        )
        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory).get(WeatherViewModel::class.java)
        cityLookupFragment = CityLookup(weatherViewModel)
        recyclerFragment = WeatherRecycler(weatherViewModel)
        detailedForecastFragment = DetailedForecast(weatherViewModel)
        setupObservers()


        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = ai.metaData["WEATHER_API_KEY"]
        Log.d("MAIN", value.toString())

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, cityLookupFragment)
                .commit()
        }
    }

    private fun setupObservers(){
        weatherViewModel.getCity().observe(this,  { _ ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, recyclerFragment)
                .addToBackStack("cityLookUpFragment")
                .commit()
        })
        weatherViewModel.getFocusedForecast().observe(this, { _ ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailedForecastFragment)
                .addToBackStack("weatherRecyclerFragment")
                .commit()
        })
    }
}