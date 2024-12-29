package com.example.weatherapp.service

import com.example.weatherapp.model.CurrentResponeApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") Apikey: String,
    ): Call<CurrentResponeApi>
}