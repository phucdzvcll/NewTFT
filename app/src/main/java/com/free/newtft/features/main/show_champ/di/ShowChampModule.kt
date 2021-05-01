package com.free.newtft.features.main.show_champ.di

import com.free.newtft.features.main.show_champ.ShowChampsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val showChampModule = module {
    viewModel {
        ShowChampsViewModel(
            appDispatchers = get(),
            getListChampsUseCase = get()
        )
    }
}