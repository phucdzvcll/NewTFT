package com.free.data

import com.free.data.entities.ChampBuilderResponse
import retrofit2.http.GET

interface TeamBuilderApiService {
    @GET("champions")
    suspend fun getListChampsBuilder(): List<ChampBuilderResponse>
}