package com.leyline.weatherapp.network

import com.leyline.weatherapp.repo.IWeatherNetwork
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private var BASE_URL:String = "https://api.openweathermap.org/data/2.5/"
    private fun getRetrofit(): Retrofit {
        val loggging = HttpLoggingInterceptor()
        loggging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggging)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val weatherApi: IWeatherNetwork = getRetrofit().create(IWeatherNetwork::class.java)
}