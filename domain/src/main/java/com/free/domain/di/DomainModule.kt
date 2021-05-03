package com.free.domain.di

import com.free.domain.usecases.show_champ.*
import com.free.domain.usecases.champ_detail.GetChampDetailUseCase
import com.free.domain.usecases.champ_detail.GetTraitsDetailUseCase
import com.free.domain.usecases.team_builder.GetListChampsBuilderUseCase
import com.free.domain.usecases.team_recommend.GetChampDialogUseCase
import com.free.domain.usecases.team_recommend.GetListTeamsRecommendUseCase
import com.free.domain.usecases.weapon.GetListWeaponUseCase
import com.free.domain.usecases.weapon_detail.GetListRecipeUseCase
import com.free.domain.usecases.weapon_detail.GetWeaponDetailUseCase
import org.koin.dsl.module

val createDomainModule = module {
    factory { GetListChampsUseCase(champsRepository = get()) }

    factory { GetListTeamsRecommendUseCase(teamsRecommendRepository = get()) }

    factory { GetChampDetailUseCase(champDetailRepository = get()) }

    factory { GetTraitsDetailUseCase(champDetailRepository = get()) }

    factory { GetChampDialogUseCase(champDialogRepository = get()) }

    factory { GetListWeaponUseCase(weaponRepository = get()) }

    factory { GetWeaponDetailUseCase(weaponDetailRepository = get()) }

    factory { GetListRecipeUseCase(weaponDetailRepository = get()) }

    factory { GetListChampsBuilderUseCase(teamBuilderRepository = get()) }
}