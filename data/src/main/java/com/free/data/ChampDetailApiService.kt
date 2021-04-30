package com.free.data

import com.free.data.entities.ChampDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ChampDetailApiService {
    @GET("champions/{id}")
    suspend fun getDetailChamp(@Path(value = "id") id: String): ChampDetailResponse
}