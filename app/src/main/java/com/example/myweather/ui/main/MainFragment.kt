package com.example.myweather.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.myweather.SearchCityActivity
import com.example.myweather.adapters.MyBindingComponent
import com.example.myweather.databinding.WeatherCityItemBinding
import com.example.myweather.db.entity.Weather
import com.google.android.material.appbar.MaterialToolbar

class MainFragment : Fragment() {

    private val TAG :String = "MainFragment"

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v : View = inflater.inflate(R.layout.fragment_main, container, false)
        val toolbar : MaterialToolbar = v.findViewById(R.id.app_bar)
        val viewManager = LinearLayoutManager(context)
        val viewAdapter = MainAdapter()
        recyclerView = v.findViewById<RecyclerView>(R.id.main_recycle_view).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        toolbar.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.menu_item_search -> {
                    Log.d(TAG, "tap on item search")
                    val i : Intent = Intent(activity, SearchCityActivity::class.java)
                    startActivity(i)
                    true
                }
                else -> {
                    false
                }
            }
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.weather.observe(viewLifecycleOwner, Observer { weather1 ->
            weather1?.let { (recyclerView.adapter as MainAdapter).setWeather(it) }
        })
        // TODO: Use the ViewModel
    }


    private class MainHolder(val itemBinding: WeatherCityItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(weather : Weather) {
            itemBinding.weather = weather
        }

    }

    private class MainAdapter : RecyclerView.Adapter<MainHolder>() {

        private var weather : List<Weather> = listOf()


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            val itemBinding = DataBindingUtil.inflate<WeatherCityItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.weather_city_item,
                parent,
                false,
                MyBindingComponent()
            )

            return MainHolder(itemBinding)
        }

        override fun getItemCount() = weather.size

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            holder.bind(weather[position])
        }

        fun setWeather(data : List<Weather>) {
            weather = data
            notifyDataSetChanged()
        }
    }
}