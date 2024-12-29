package com.example.weatherapp.server

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private lateinit var retrofit: Retrofit //Khai bao bien ten retrofit co kieu du lieu la Retrofit

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build() //Cau hinh doi tuong OkHttpClient ket noi, doc, ghi trong 60s

    fun getClient(): Retrofit {//Cau hinh retrofit(baseUrl,client,convert) de giao tiep API thong qua Http
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org") // duong link base
            .client(client) //cau hinh
            .addConverterFactory(GsonConverterFactory.create()) // chuyen doi tu Json thanh cac doi tuong
            .build()
        return retrofit
    }
}