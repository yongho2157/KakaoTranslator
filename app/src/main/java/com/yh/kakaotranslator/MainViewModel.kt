package com.yh.kakaotranslator

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

    fun getSentenceTranslation(query: String, srcLang: String, targetLang: String) {
        repository.getText(
            query,
            srcLang,
            targetLang,
            onSuccess = {
                _mainViewStateLiveData.value = MainViewState.GetText(it.translatedText)
            },
            onFailure = {

            }
        )
    }

}