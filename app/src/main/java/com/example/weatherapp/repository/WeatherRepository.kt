package com.example.weatherapp.repository

import com.example.weatherapp.service.ApiServices

class WeatherRepository(
    val api: ApiServices, //Tao doi tuong
) {
    //Lay du lieu theo lat,lon,unit
    fun getCurrentWeather(lat: Double, lon: Double, unit: String) =
        api.getCurrentWeather(lat, lon, unit, "b4b15e9ca3915e6619b1c9ef47c9750f")
}