package com.koinbond.apps.network

import com.koinbond.apps.BuildConfig
import com.koinbond.apps.base.ResponseArray
import io.reactivex.Observable

class DataManager(private val apiService: ApiService) {
    internal var apiServices = apiService

    companion object {
        private val Retrofit: retrofit2.Retrofit? = null

        fun getApiService(): ApiService {
            return RetrofitClient.getClient(BuildConfig.BASE_URL)!!.create(ApiService::class.java)
        }

    }

    //Add Code Below
    fun getExample(): Observable<ResponseArray<String>>{
        return apiService.getExample()
    }
}