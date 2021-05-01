package com.free.data

import com.free.data.entities.ChampDialogResponse
import com.free.data.entities.ChampionResponse
import retrofit2.http.*

interface ChampDialogApiService {
    @GET("champions/{id}")
    suspend fun getChampsList(@Path("id") id: String): ChampDialogResponse
}