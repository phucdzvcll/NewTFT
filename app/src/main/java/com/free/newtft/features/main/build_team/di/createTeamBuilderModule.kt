package com.free.newtft.features.main.build_team.di

import com.free.newtft.features.main.build_team.viewmodel.TeamBuilderViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createTeamBuilderModule = module {

    viewModel {
        TeamBuilderViewModel(
            appDispatchers = get(),
            getListChampsBuilderUseCase = get()
        )
    }

}