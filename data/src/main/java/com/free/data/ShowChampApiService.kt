package com.free.data

import com.free.data.entities.ChampionResponse
import retrofit2.http.*

interface ShowChampApiService {
    @GET("champions")
    suspend fun getChampsList(): List<ChampionResponse>
}