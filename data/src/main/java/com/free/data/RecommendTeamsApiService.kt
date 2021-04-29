package com.free.data

import com.free.data.entities.TeamsResponse
import retrofit2.http.GET

interface RecommendTeamsApiService {
    @GET("recommendation")
    suspend fun getTeamsRecommend(): List<TeamsResponse>

}