package com.example.demo_kotlin.enums

enum class ViewModelStatus {
    INIT, LOADING, SUCCESS
}

fun ViewModelStatus.isLoading(): Boolean {
    return this == ViewModelStatus.LOADING;
}

