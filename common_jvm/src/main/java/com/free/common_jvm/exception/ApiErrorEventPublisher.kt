package com.free.common_jvm.exception

interface ApiErrorEventPublisher {
    fun onApiError(errorCode: Int, apiUrl: String, errorMessage: String?)
}