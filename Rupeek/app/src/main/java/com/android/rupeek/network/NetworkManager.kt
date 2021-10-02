package com.android.rupeek.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.rupeek.data.WeatherAppData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class NetworkManager(private val listener:onFailure) {
    private var gson = Gson()
    var mutableLiveData: MutableLiveData<WeatherAppData> = MutableLiveData()
    private val responseList = ArrayList<WeatherAppData>()

    fun makeNetworkCall(){
        val apiService = ApiClient().getClient()?.create(ApiInterfaces::class.java)
        val call: Call<WeatherAppData>? = apiService?.getWeatherData()
        call?.enqueue(object : Callback<WeatherAppData> {
            override fun onFailure(call: Call<WeatherAppData>, t: Throwable) {
                Log.d("APiCall","onFailure with message = ${t.localizedMessage}")
                listener.onError(t.localizedMessage!!)
            }

            override fun onResponse(call: Call<WeatherAppData>, response: Response<WeatherAppData>) {
                Log.d("APiCall","onResponse with data = ${response.body()?.data}")
                mutableLiveData.postValue(response.body())
            }
        })
    }

    fun getLatestAppData(): MutableLiveData<WeatherAppData> {
        return mutableLiveData
    }

    interface onFailure{
        fun onError(message:String)
    }
}