package com.free.data

import com.free.data.entities.ChampionsListResponse
import retrofit2.http.*

interface ShowChampApiService {
    @GET("champions")
    suspend fun getChampsList(): ChampionsListResponse
}