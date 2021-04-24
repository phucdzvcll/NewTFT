package com.free.data

import com.free.data.entities.TeamsListResponse
import retrofit2.http.GET

interface RecommendTeamsApiService {
    @GET("recommendation")
    suspend fun getTeamsRecommend(): TeamsListResponse

}