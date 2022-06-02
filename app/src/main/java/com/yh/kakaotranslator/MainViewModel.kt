package com.yh.kakaotranslator

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yh.kakaotranslator.data.repo.KakaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: KakaoRepository) : ViewModel() {

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState>
        get() = _mainViewStateLiveData

    val inputTextLiveData = MutableLiveData<String>()
    val langObservableField = ObservableField<String>()

    fun translate() {
        if (!inputTextLiveData.value.isNullOrEmpty()) {
            repository.getText(
                query = inputTextLiveData.value!!,
                srcLang = "kr",
                targetLang = langObservableField.get()!!,
                onSuccess = {

                    Log.d("결과", "translate onSuccess")
                    _mainViewStateLiveData.value = MainViewState.GetText(it.translated_text)
                },
                onFailure = {

                }
            )
        }
    }

}