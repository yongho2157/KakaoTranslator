package com.yh.kakaotranslator

sealed class MainViewState {
    data class GetText(val text: List<List<String>>): MainViewState()
}