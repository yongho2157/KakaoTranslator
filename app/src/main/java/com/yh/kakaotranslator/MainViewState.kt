package com.yh.kakaotranslator

sealed class MainViewState {
    data class GetText(val text: List<List<String>>) : MainViewState()
    data class Error(val message: String) : MainViewState()
}