package com.free.newtft.features.dialog_item.di

import com.free.newtft.features.dialog_item.viewmodel.WeaponDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createWeaponDetailModule = module {
    viewModel {
        WeaponDetailViewModel(
            appDispatchers = get(),
            getListRecipeUseCase = get(),
            getWeaponDetailUseCase = get()
        )
    }
}