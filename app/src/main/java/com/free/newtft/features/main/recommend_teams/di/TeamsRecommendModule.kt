package com.free.newtft.features.main.recommend_teams.di

import com.free.newtft.features.main.recommend_teams.viewmodel.TeamsRecommendViewModel
import com.free.newtft.features.main.recommend_teams.viewmodel.ChampDialogViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val teamsRecommendModule = module {
    viewModel {
        TeamsRecommendViewModel(
            appDispatchers = get(),
            getListTeamsRecommendUseCase = get()
        )
    }

    viewModel {
        ChampDialogViewModel(
            appDispatchers = get(),
            getChampDialogUseCase = get()
        )
    }
}