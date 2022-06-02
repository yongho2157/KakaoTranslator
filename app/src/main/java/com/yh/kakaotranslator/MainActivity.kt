package com.yh.kakaotranslator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.yh.kakaotranslator.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initViewModel()
    }

    private fun initUi() {
        initSpinner()
    }

    private fun initViewModel() {
        binding.viewModel = mainViewModel

        mainViewModel.mainViewStateLiveData.observe(this) { viewState ->

            when (viewState) {

                is MainViewState.GetText -> {
                    binding.result.text.clear()
                    binding.result.setText(viewState.text[0][0])
                }
            }
        }
    }

    private fun initSpinner() {
        val items = resources.getStringArray(R.array.lang_arr)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, items)
        binding.spinner.adapter = myAdapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                mainViewModel.langObservableField.set(binding.spinner.selectedItem.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }
}