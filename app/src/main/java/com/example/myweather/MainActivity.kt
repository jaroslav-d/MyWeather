package com.example.myweather

import androidx.fragment.app.Fragment
import com.example.myweather.ui.main.MainFragment

class MainActivity : SingleFragmentActivity() {

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    override fun createFragment(): Fragment {
        return MainFragment.newInstance()
    }

}