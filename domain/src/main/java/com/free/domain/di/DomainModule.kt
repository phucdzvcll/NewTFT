package com.free.domain.di

import com.free.domain.usecases.show_champ.GetListChampsUseCase
import org.koin.dsl.module

val createDomainModule = module {
    factory { GetListChampsUseCase(champsRepository = get()) }
}