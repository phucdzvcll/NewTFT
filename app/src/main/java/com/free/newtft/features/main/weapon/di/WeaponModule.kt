package com.free.newtft.features.main.weapon.di

import com.free.newtft.features.main.weapon.viewmodel.WeaponViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createWeaponModule = module {
    viewModel {
        WeaponViewModel(
            appDispatchers = get(),
            getListWeaponUseCase = get()
        )
    }
}