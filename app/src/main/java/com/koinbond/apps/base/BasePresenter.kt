package com.koinbond.apps.base

import android.util.Log
import com.google.gson.GsonBuilder
import com.koinbond.apps.BuildConfig.BASE_URL
import com.koinbond.apps.network.ApiService
import com.koinbond.apps.network.DataManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract class BasePresenter<MvpView :BaseView>( val view:MvpView) {

    var dataManager : DataManager
    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create<ApiService>(ApiService::class.java)
        dataManager = DataManager(api)
    }
}