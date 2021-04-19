package com.free.common_android.di

import com.free.common_android.AppDispatchers
import com.free.common_android.navigation.NavigateViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createCommonModule = module {
    single { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }

    viewModel { NavigateViewModel() }
}