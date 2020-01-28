package com.koinbond.apps.utils

import android.util.Log
import com.google.gson.Gson
import com.koinbond.apps.base.BaseExeption
import com.koinbond.apps.base.BaseResponse
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

object RxUtils {
    fun <T> applyScheduler(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it
        }
    }

    fun <T : BaseResponse> applyResponseErrorHandler(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.flatMap { res ->
                if (res == null){
                    return@flatMap Observable.error<T>(NullPointerException())
                } else if (res.status == "success"){
                    return@flatMap Observable.just(res)
                }
                return@flatMap Observable.error<T>(BaseExeption(res))
            }
        }
    }

    fun <T> applyApiCall(): ObservableTransformer<T, T> {
        return ObservableTransformer { tObservable ->
            tObservable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .doOnError {
                    it.printStackTrace()
                }
                .onErrorResumeNext { throwable: Throwable ->
                    throwable.printStackTrace()
                    Log.d("rrr",throwable.message)
                    if (throwable is HttpException) {
                        try {
                            val res = throwable.response()?.raw()?.body.toString()
                            Log.d("zzz res", Gson().toJson(throwable))
                            val gson = Gson()
                            val baseResponse = gson.fromJson(res, BaseResponse::class.java)
                            val e =
                                throwable.response()?.code()?.let { BaseExeption(baseResponse, it) }
                            Log.d("zzz","on error resume next")
                            return@onErrorResumeNext Observable.error(e)
//                                return@onErrorResumeNext Observable.error(throwable)

                        } catch (e: IOException) {
                            return@onErrorResumeNext Observable.error(e)
//                                Observable.empty<T>()
                        }
                    } else {
                        return@onErrorResumeNext Observable.error(throwable)
//                            Observable.empty<T>()
                    }
                }
                .flatMap { res ->
                    if (res == null){
                        Log.d("xxx","null")
                        return@flatMap Observable.error<T>(NullPointerException())
                    } else if ((res as BaseResponse).success || (res as BaseResponse).status == "success" || (res as BaseResponse).status =="OK"){
                        Log.d("xxx","next")
                        return@flatMap Observable.just(res as T)
                    }
                    Log.d("xxx","error")
                    return@flatMap Observable.error<T>(BaseExeption(res as BaseResponse))
                }
        }
    }
}