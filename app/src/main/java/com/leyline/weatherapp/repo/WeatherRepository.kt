package com.leyline.weatherapp.repo

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leyline.weatherapp.network.WeatherApi
import com.leyline.weatherapp.repo.models.Forecast
import com.leyline.weatherapp.repo.models.WeatherForecast
import com.leyline.weatherapp.viewmodel.Weather.IWeatherRepo
import retrofit2.Call
import retrofit2.Response

class WeatherRepository(
    private val weatherApi: WeatherApi,
    applicationContext: Context
): IWeatherRepo{
    private lateinit var currentCity: String
    private var apiKey: String

    private val liveReport = MutableLiveData<WeatherForecast>()
    private val liveForecasts = MutableLiveData<List<Forecast>>()
    private val focusedForecast = MutableLiveData<Forecast>()

    override fun getCity() = liveReport as LiveData<WeatherForecast>
    override fun getForecasts() = liveForecasts as LiveData<List<Forecast>>
    override fun getFocusedForecast() = focusedForecast as LiveData<Forecast>

    init{
        val applicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        apiKey = applicationInfo.metaData["WEATHER_API_KEY"] as String
    }

    private fun updateLiveData(response: WeatherForecast){
        liveReport.value = response
        liveForecasts.value = response.list
    }

    override fun setFocusedForecast(forecast: Forecast) {
        focusedForecast.value = forecast
    }

    override fun getCityName(): String{
        return currentCity
    }
    override fun setCityName(cityName: String) {
        currentCity = cityName
    }

    override suspend fun getForecastReportFor(cityName: String) {
        apiCallAndSaveToRepo(cityName)
    }

    private fun apiCallAndSaveToRepo(cityName: String){
        apiWeatherForCity(cityName).enqueue(object : retrofit2.Callback<WeatherForecast>{
            override fun onResponse(
                call: Call<WeatherForecast>,
                response: Response<WeatherForecast>
            ){
                if(response.isSuccessful && response.body() != null){
                    when(response.code()){
                        200 ->{
                            updateLiveData(response.body()!!)
                            setCityName(cityName)
                        }
                        404 ->{
                            Log.e("Weather Repository", "City Not Found")
                        }
                        else -> {Log.e("Weather Repository", response.code().toString())}
                    }
                }
            }
            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                Log.e("Weather Repository", t.message.toString())
            }
        })
    }

    // API METHODS
    private fun apiWeatherForCity(cityName: String) = weatherApi.getWeatherForCity(cityName, apiKey)

}
