package com.free.domain.di

import com.free.domain.usecases.show_champ.GetListChampsUseCase
import com.free.domain.usecases.show_champ.GetListTeamsRecommendUseCase
import org.koin.dsl.module

val createDomainModule = module {
    factory { GetListChampsUseCase(champsRepository = get()) }

    factory { GetListTeamsRecommendUseCase(teamsRecommendRepository = get()) }
}