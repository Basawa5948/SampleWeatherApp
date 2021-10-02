package com.android.rupeek.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class WeatherAppData {

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null
}