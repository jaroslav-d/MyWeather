package com.example.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myweather.ui.search.SearchCityFragment

class SearchCityActivity : SingleFragmentActivity() {

    override fun getLayout(): Int {
        return R.layout.fragment_search_city
    }

    override fun createFragment(): Fragment {
        return SearchCityFragment.newInstance()
    }

}