package com.yh.kakaotranslator.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.repo.kakao.KakaoRepository
import com.yh.kakaotranslator.MainViewState
import com.yh.kakaotranslator.base.BaseViewModel
import com.example.core.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application,
    private val repository: KakaoRepository
) : BaseViewModel(app) {

    val inputTextLiveData = MutableLiveData<String>()
    val langObservableField = ObservableField<String>()

    fun translate() {
        CoroutineScope(Dispatchers.IO).launch {
            if (!inputTextLiveData.value.isNullOrEmpty()) {
                when (val result = repository.getText(
                    query = inputTextLiveData.value!!,
                    srcLang = "kr",
                    targetLang = langObservableField.get()!!,
                )) {
                    is Result.Success -> {
                        viewStateChange(MainViewState.GetText(result.data.translated_text))
                    }
                    is Result.Error -> {
                        viewStateChange(MainViewState.Error("에러가 발생."))
                    }
                }
            }
        }
    }
}