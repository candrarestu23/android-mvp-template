package com.koinbond.apps.base

import android.app.Application

open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        initFabric()
//        Hawk.init(this).build()
    }
//    private fun initFabric() {
//        // Set up Crashlytics, disabled for debug builds
//        val crashlyticsKit = Crashlytics.Builder()
//            .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
//            .build()
//
//        // Initialize Fabric with the debug-disabled crashlytics.
//        Fabric.with(this, crashlyticsKit)
//    }
}