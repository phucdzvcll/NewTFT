package com.free.newtft.features.details.champ_detail.di

import com.free.newtft.features.details.champ_detail.viewmodel.ChampDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val champDetailModule = module {
    viewModel {
        ChampDetailViewModel(
            appDispatchers = get(), getTraitsDetailUseCase = get(), getChampDetailUseCase = get()
        )
    }
}