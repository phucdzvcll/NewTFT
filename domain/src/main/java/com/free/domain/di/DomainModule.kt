package com.free.domain.di

import com.free.domain.usecases.show_champ.*
import org.koin.dsl.module

val createDomainModule = module {
    factory { GetListChampsUseCase(champsRepository = get()) }

    factory { GetListTeamsRecommendUseCase(teamsRecommendRepository = get()) }

    factory { GetChampDetailUseCase(champDetailRepository = get()) }

    factory { GetTraitsDetailUseCase(champDetailRepository = get()) }

    factory { GetChampDialogUseCase(champDialogRepository = get()) }

    factory { GetListWeaponUseCase(weaponRepository = get()) }
}