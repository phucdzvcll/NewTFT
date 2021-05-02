package com.free.data

import com.free.data.entities.WeaponDetailResponse
import com.free.data.entities.WeaponResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeaponApiService {
    @GET("items")
    suspend fun getListWeaponResponse(@Query("isElement") isElement: Boolean): List<WeaponResponse>

    @GET("items")
    suspend fun getListWeaponDetail(@Query("name") name: String): List<WeaponDetailResponse>
}