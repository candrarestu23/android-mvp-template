package com.koinbond.apps.feature.example

import android.annotation.SuppressLint
import com.koinbond.apps.base.BasePresenter
import com.koinbond.apps.utils.ErrorHandler
import com.koinbond.apps.utils.RxUtils

/**
 * Created by Candra Restu on 29,January,2020
 */

class ExamplePresenter(mvpView: ExampleView) : BasePresenter<ExampleView>(mvpView){

    @SuppressLint("CheckResult")
    fun getExample(){
        view.onLoading(true)
        dataManager.getExample()
            .doOnTerminate { view.onLoading(false) }
            .compose(RxUtils.applyScheduler())
            .compose(RxUtils.applyApiCall())
            .subscribe({
                view.onSuccessExample(it.data!!)
            },{
                    t: Throwable? ->
                val message = ErrorHandler.handleError(t)
                view.onFailed(message)
            })
    }
}