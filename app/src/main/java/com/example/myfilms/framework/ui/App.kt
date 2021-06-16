package com.example.myfilms.framework.ui

import android.app.Application
import com.example.myfilms.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//para koin
class App: Application () {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}