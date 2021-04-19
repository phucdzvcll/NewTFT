package com.free.newtft.di

import android.app.Application
import com.free.common_android.di.createCommonModule
import com.free.data.di.createRemoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(createCommonModule, createRemoteModule)
        }
    }
}