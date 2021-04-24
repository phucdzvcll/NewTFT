package com.free.newtft.features.recommend_teams.di

import com.free.newtft.features.recommend_teams.TeamsRecommendViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val teamsRecommendModule = module {
    viewModel {
        TeamsRecommendViewModel(
            appDispatchers = get(),
            getListTeamsRecommendUseCase = get()
        )
    }
}