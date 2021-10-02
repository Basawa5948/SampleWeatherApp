package com.android.rupeek.view

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.rupeek.R

class MainActivity : AppCompatActivity() {
    companion object{
        val TAG = MainActivity::class.java.simpleName
        val WEATHER_FRAGMENT = WeatherDataFragment::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null && isNetworkAvailable()){
            loadDataFragment()
        }else{
            Toast.makeText(this,"No  Internet Connection", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun loadDataFragment() {
        val dataFragment = supportFragmentManager.findFragmentByTag(WEATHER_FRAGMENT)
        if(dataFragment==null){
            supportFragmentManager.beginTransaction().add(R.id.weatherFragment,
                WeatherDataFragment.newInstance("",""), WEATHER_FRAGMENT)
                .commit()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}