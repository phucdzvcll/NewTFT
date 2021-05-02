package com.free.newtft.di

import android.app.Application
import com.free.common_android.di.createCommonModule
import com.free.data.di.createRemoteModule
import com.free.domain.di.createDomainModule
import com.free.newtft.features.details.champ_detail.di.champDetailModule
import com.free.newtft.features.dialog_item.di.createWeaponDetailModule
import com.free.newtft.features.main.recommend_teams.di.teamsRecommendModule
import com.free.newtft.features.main.show_champ.di.showChampModule
import com.free.newtft.features.main.weapon.di.createWeaponModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                createCommonModule,
                createRemoteModule,
                createDomainModule,
                showChampModule,
                teamsRecommendModule,
                champDetailModule,
                createWeaponModule,
                createWeaponDetailModule
            )
        }
    }
}