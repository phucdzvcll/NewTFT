package com.free.data

import com.free.data.entities.TraitOfTeamRecommend
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TraitsOfTeamRecommendService {
    @GET("traits")
    suspend fun getTraitsOfTeamsRecommend(@Query("key") listTraitsName: List<String>): List<TraitOfTeamRecommend>
}