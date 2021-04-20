package com.free.data.di

import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.repository.ChampsRepositoryImpl
import com.free.domain.repositories.ChampsRepository
import org.koin.dsl.module

val createRemoteModule = module {
    single { RemoteExceptionInterceptor() }

    single<ChampsRepository> { ChampsRepositoryImpl(remoteExceptionInterceptor = get()) }
}