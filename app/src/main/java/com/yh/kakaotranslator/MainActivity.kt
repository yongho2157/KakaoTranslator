package com.yh.kakaotranslator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.yh.kakaotranslator.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.mainViewStateLiveData.observe(this) {

        }

        val items = resources.getStringArray(R.array.lang_arr)

        val myAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, items)

        binding.spinner.adapter = myAdapter
        Log.d("test0601", binding.spinner.selectedItem.toString())
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("test0530", items[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }
}