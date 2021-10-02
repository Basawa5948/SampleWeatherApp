package com.android.rupeek.network

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit

class ApiClient {
    val BASE_URL = "https://www.mocky.io/v2/"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}