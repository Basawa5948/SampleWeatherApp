package com.android.rupeek.network

import com.android.rupeek.data.WeatherAppData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterfaces {
    @GET("5d3a99ed2f0000bac16ec13a/")
    fun getWeatherData(): Call<WeatherAppData>?
}