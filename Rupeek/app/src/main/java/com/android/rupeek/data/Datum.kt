package com.android.rupeek.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("temp")
    @Expose
    private var temp: Int? = null

    @SerializedName("time")
    @Expose
    private var time: Int? = null

    @SerializedName("rain")
    @Expose
    private var rain: Int? = null

    @SerializedName("wind")
    @Expose
    private var wind: Int? = null

    fun getTemp(): Int? {
        return temp
    }

    fun setTemp(temp: Int?) {
        this.temp = temp
    }

    fun getTime(): Int? {
        return time
    }

    fun setTime(time: Int?) {
        this.time = time
    }

    fun getRain(): Int? {
        return rain
    }

    fun setRain(rain: Int?) {
        this.rain = rain
    }

    fun getWind(): Int? {
        return wind
    }

    fun setWind(wind: Int?) {
        this.wind = wind
    }
}