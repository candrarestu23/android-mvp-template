package com.koinbond.apps.base


interface BaseView {
    fun onLoading(isLoading: Boolean)
    fun onFailed(message: String)
}
