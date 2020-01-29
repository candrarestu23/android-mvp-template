package com.koinbond.apps.network

import com.koinbond.apps.base.ResponseArray
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/")
    fun getExample(
    ): Observable<ResponseArray<String>>
}