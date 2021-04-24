package com.free.data.di

import com.free.data.RecommendTeamsApiService
import com.free.data.ShowChampApiService
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.ChampsListMapper
import com.free.data.map.TeamRecommendListMapper
import com.free.data.repository.ChampsRepositoryImpl
import com.free.data.repository.TeamsRecommendResponseImpl
import com.free.domain.repositories.ChampsRepository
import com.free.domain.repositories.TeamsRecommendRepository
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

    factory { ChampsListMapper() }
    factory { TeamRecommendListMapper() }
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
            teamsRecommendListMapper = get()
        )
    }
}
