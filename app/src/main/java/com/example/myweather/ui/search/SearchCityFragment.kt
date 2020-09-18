package com.example.myweather.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.myweather.adapters.MyBindingComponent
import com.example.myweather.databinding.SearchCityItemBinding
import com.example.myweather.service.models.CityInfo

class SearchCityFragment : Fragment() {

    companion object {
        fun newInstance() = SearchCityFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SearchCityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v : View = inflater.inflate(R.layout.fragment_search_city, container, false)

        val viewManager = LinearLayoutManager(context)
        val viewAdapter = SearchAdapter()
        recyclerView = v.findViewById<RecyclerView>(R.id.search_city_fragment_recycle_view).apply {
//            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val searchEditText = v.findViewById<EditText>(R.id.search_city_fragment_edit_text)
        searchEditText.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val text = textView.text
                (recyclerView.adapter as SearchAdapter).setCities(viewModel.getCities(text.toString()))
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchCityViewModel::class.java)
    }


    private class SearchHolder(val itemBinding: SearchCityItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(cityInfo: CityInfo) {
            itemBinding.city = cityInfo
        }
    }

    private class SearchAdapter : RecyclerView.Adapter<SearchHolder>() {

        private var cities : List<CityInfo> = listOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
            val itemBinding = DataBindingUtil.inflate<SearchCityItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.search_city_item,
                parent,
                false,
                MyBindingComponent()
            )

            return SearchHolder(itemBinding)
        }

        override fun getItemCount() = cities.size

        override fun onBindViewHolder(holder: SearchHolder, position: Int) {
            holder.bind(cities[position])
        }

        fun setCities(data : List<CityInfo>) {
            cities = data
            notifyDataSetChanged()
        }
    }
}