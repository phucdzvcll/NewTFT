package com.free.data

import com.free.data.entities.TraitOfTeamRecommendResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TraitsOfTeamRecommendService {
    @GET("traits")
    suspend fun getTraitsOfTeamsRecommend(@Query("key") keys: List<String>): List<TraitOfTeamRecommendResponse>
}