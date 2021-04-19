package com.free.common_android

import kotlinx.coroutines.CoroutineDispatcher

class AppDispatchers(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher
)