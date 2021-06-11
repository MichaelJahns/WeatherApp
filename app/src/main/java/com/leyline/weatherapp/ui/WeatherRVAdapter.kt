package com.leyline.weatherapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leyline.weatherapp.R
import com.leyline.weatherapp.repo.models.Forecast

class WeatherRVAdapter(
    private var weatherClickedListener: OnWeatherClickedListener,
    private val weatherList: List<Forecast>
): RecyclerView.Adapter<WeatherRVAdapter.ForecastHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_row, parent, false)
        return ForecastHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastHolder, position: Int) {
        val temperatureString = "Temp : " +  weatherList[position].main.temp.toString() + " C"
        holder.weatherSystem.text = weatherList[position].weather[0].main
        holder.temperature.text = temperatureString
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    inner class ForecastHolder(v: View):
        RecyclerView.ViewHolder(v),
        View.OnClickListener
    {
        val temperature: TextView = v.findViewById(R.id.tvTemperature)
        val weatherSystem: TextView = v.findViewById(R.id.labelWeatherSystem)

        init{
            v.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION){
                weatherClickedListener.onWeatherClicked(weatherList[position])
            }
        }
    }

    interface OnWeatherClickedListener{
        fun onWeatherClicked(forecast: Forecast)
    }
}