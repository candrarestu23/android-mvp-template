package com.koinbond.apps.base

class ResponseArray<Model> : BaseResponse() {
    val data: List<Model>? = null
    val total: Int? = null
}
