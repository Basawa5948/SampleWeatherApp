package com.android.rupeek.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.proximityapp.adapter.WeatherAdapter
import com.android.rupeek.R
import com.android.rupeek.viewModel.WeatherDataViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherDataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var weatherDataViewModel: WeatherDataViewModel
    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var mContext: Context
    private lateinit var myAQIAdapter:WeatherAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var rootLayout:RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            weatherDataViewModel = ViewModelProvider(this).get(WeatherDataViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_data, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherRecyclerView = view.findViewById(R.id.weather_recyclerView)
        rootLayout = view.findViewById(R.id.rootLayout)
        progressBar = ProgressBar(mContext,null, android.R.attr.progressBarStyleLarge)
        val params = RelativeLayout.LayoutParams(100, 100)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        rootLayout.addView(progressBar, params)
        progressBar.visibility = View.VISIBLE
        weatherRecyclerView.layoutManager = LinearLayoutManager(mContext)
        lookForData()
        lookForError()
    }

    private fun lookForError() {
        weatherDataViewModel.mutableLiveErrorData.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE
            Toast.makeText(mContext,"it",Toast.LENGTH_LONG).show()
            activity?.finish()
        })
    }

    private fun lookForData() {
        weatherDataViewModel.initiateConnection()
        weatherDataViewModel.getLatestWeatherData().observe(viewLifecycleOwner, Observer { listOfWeatherData ->
            progressBar.visibility = View.GONE
            myAQIAdapter = WeatherAdapter(mContext,listOfWeatherData)
            weatherRecyclerView.adapter = myAQIAdapter
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeatherDataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WeatherDataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}