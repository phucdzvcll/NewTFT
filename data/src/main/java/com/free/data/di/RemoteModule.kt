package com.free.data.di

import com.free.data.*
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.*
import com.free.data.repository.*
import com.free.domain.repositories.*
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val createRemoteModule = module {
    single { RemoteExceptionInterceptor() }

    factory {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://fake-api-json-server-p5k.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(ShowChampApiService::class.java) }
    factory { get<Retrofit>().create(RecommendTeamsApiService::class.java) }
    factory { get<Retrofit>().create(TraitsOfTeamRecommendService::class.java) }
    factory { get<Retrofit>().create(ChampDetailApiService::class.java) }
    factory { get<Retrofit>().create(ChampDialogApiService::class.java) }
    factory { get<Retrofit>().create(WeaponApiService::class.java) }

    factory { ChampsListMapper() }
    factory { TeamRecommendMapper() }
    factory { TraitsListMapper() }
    factory { ChampDetailMapper() }
    factory { TraitsDetailMapper() }
    factory { ChampOfTraitDetailMapper() }
    factory { ChampDialogMapper() }
    factory { WeaponMapper() }

    single<ChampsRepository> {
        ChampsRepositoryImpl(
            remoteExceptionInterceptor = get(),
            champsListMapper = get(),
            showChampApiService = get()
        )
    }

    single<TeamsRecommendRepository> {
        TeamsRecommendResponseImpl(
            remoteExceptionInterceptor = get(),
            recommendTeamsApiService = get(),
            teamsRecommendMapper = get(),
            traitsOfTeamRecommendService = get(),
            traitsListMapper = get()
        )
    }

    single<ChampDetailRepository> {
        ChampDetailRepositoryImpl(
            remoteExceptionInterceptor = get(),
            champDetailApiService = get(),
            champOfTraitDetailMapper = get(),
            traitsDetailMapper = get(),
            champDetailMapper = get()
        )
    }

    single<ChampDialogRepository> {
        ChampDialogRepositoryImpl(
            remoteExceptionInterceptor = get(),
            champDialogApiService = get(),
            champDialogMapper = get()
        )
    }

    single<WeaponRepository> {
        WeaponRepositoryImpl(
            remoteExceptionInterceptor = get(),
            weaponApiService = get(),
            weaponMapper = get()
        )
    }
}
