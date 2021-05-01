package com.free.data

import com.free.data.entities.ChampDetailResponse
import com.free.data.entities.ChampionResponse
import com.free.data.entities.TraitDetailResponse
import com.free.domain.entities.TraitDetailEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChampDetailApiService {
    @GET("champions/{id}")
    suspend fun getDetailChamp(@Path(value = "id") id: String): ChampDetailResponse

    @GET("traits")
    suspend fun getTraitDetail(@Query("key") keys: List<String>): List<TraitDetailResponse>

    @GET("champions")
    suspend fun getChampsByTrait(@Query("traits_like") trait: String): List<ChampionResponse>
}