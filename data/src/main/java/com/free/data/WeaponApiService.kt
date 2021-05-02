package com.free.data

import com.free.data.entities.WeaponResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeaponApiService {
    @GET("items")
    suspend fun getListWeaponResponse(@Query("isElement") isElement: Boolean): List<WeaponResponse>
}