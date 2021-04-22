package com.free.data.di

import com.free.data.ShowChampApiService
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.ChampsListMapper
import com.free.data.repository.ChampsRepositoryImpl
import com.free.domain.repositories.ChampsRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val createRemoteModule = module {
    single { RemoteExceptionInterceptor() }

    factory<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
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

    factory { ChampsListMapper() }

    single<ChampsRepository> {
        ChampsRepositoryImpl(
            remoteExceptionInterceptor = get(),
            champsListMapper = get(),
            showChampApiService = get()
        )
    }
}
