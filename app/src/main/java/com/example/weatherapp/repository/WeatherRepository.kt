package com.example.weatherapp.repository

import com.example.weatherapp.service.ApiServices

class WeatherRepository(
    val api: ApiServices, //Tao doi tuong
) {
    fun getCurrentWeather(
        lat: Double,  //vi do - latitude
        lon: Double,  //kinh do - longitude
        unit: String,
    ) = api.getCurrentWeather(lat, lon, unit, "b4b15e9ca3915e6619b1c9ef47c9750f")
}