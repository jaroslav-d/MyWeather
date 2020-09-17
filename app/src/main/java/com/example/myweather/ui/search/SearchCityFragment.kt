package com.example.myweather.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R

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
        recyclerView = v.findViewById<RecyclerView>(R.id.search_city_recycle_view).apply {
//            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

//        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    // Your piece of code on keyboard search click
//                    return true;
//                }
//                return false;
//            }
//        });

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchCityViewModel::class.java)
//        (recyclerView.adapter as SearchAdapter).setCities(viewModel.getData())

    }


    private class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    private class SearchAdapter : RecyclerView.Adapter<SearchHolder>() {

        private var cities : List<String> = listOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
            val v : View =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.search_city_item, parent, false)

            return SearchHolder(v)
        }

        override fun getItemCount() = cities.size

        override fun onBindViewHolder(holder: SearchHolder, position: Int) {
            var textView : TextView = holder.itemView.findViewById(R.id.city_item_text)
            textView.text = cities[position]
        }

        fun setCities(data : List<String>) {
            cities = data
            notifyDataSetChanged()
        }
    }
}