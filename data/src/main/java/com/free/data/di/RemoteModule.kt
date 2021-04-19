package com.free.data.di

import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import org.koin.dsl.module

val createRemoteModule = module {
    single { RemoteExceptionInterceptor() }
}