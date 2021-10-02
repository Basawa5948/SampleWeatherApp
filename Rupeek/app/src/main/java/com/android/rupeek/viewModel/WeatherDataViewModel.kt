package com.android.rupeek.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rupeek.data.WeatherAppData
import com.android.rupeek.network.NetworkManager

class WeatherDataViewModel:ViewModel(),NetworkManager.onFailure {
    private var mutableLiveWeatherData: MutableLiveData<WeatherAppData> = MutableLiveData()
    private val networkManager =  NetworkManager(this)
    var mutableLiveErrorData:MutableLiveData<String> = MutableLiveData()

    fun getLatestWeatherData(): MutableLiveData<WeatherAppData> {
        return mutableLiveWeatherData
    }

    fun initiateConnection() {
        networkManager.makeNetworkCall()
        mutableLiveWeatherData = networkManager.getLatestAppData()
    }

    override fun onError(message: String) {
        mutableLiveErrorData.postValue(message)
    }
}