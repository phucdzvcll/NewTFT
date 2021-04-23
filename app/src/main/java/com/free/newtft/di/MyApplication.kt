package com.free.newtft.di

import android.app.Application
import com.free.common_android.di.createCommonModule
import com.free.data.di.createRemoteModule
import com.free.domain.di.createDomainModule
import com.free.newtft.features.show_champ.di.showChampModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(createCommonModule, createRemoteModule, createDomainModule, showChampModule)
        }
    }
}