package com.yh.kakaotranslator

import com.yh.kakaotranslator.base.ViewState

sealed class MainViewState : ViewState{
    data class GetText(val text: List<List<String>>) : MainViewState()
    data class Error(val message: String) : MainViewState()
}