package com.android.proximityapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.rupeek.R
import com.android.rupeek.data.WeatherAppData
import java.util.*

class WeatherAdapter(val mContext: Context, val weatherData: WeatherAppData):
            RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.list_weather,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = weatherData.data?.get(position)
        with(holder){
            if (data != null) {
                temp.text = data.getTemp().toString()+"\u2103"
                date.text = parseDate(data.getTime())
                rain.text = data.getRain().toString()+"%"
                wind.text = data.getWind().toString()+" km/h"
            }
        }
    }

    private fun parseDate(time: Int?): String {
        val date = time?.toLong()?.times(1000)?.let { Date(it) }.toString()
        val firstHalf = date.substring(3,10)
        val secondHalf = date.substring(date.length-4,date.length)
        Log.d("Time", "$firstHalf and $secondHalf")
        val finalDate = "$firstHalf $secondHalf"
        return (finalDate)
    }

    override fun getItemCount(): Int {
        return weatherData.data!!.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var temp:TextView = itemView.findViewById(R.id.temp)
        var date:TextView = itemView.findViewById(R.id.date)
        var rain:TextView = itemView.findViewById(R.id.rain)
        var wind:TextView = itemView.findViewById(R.id.wind)
    }
}