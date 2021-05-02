package com.free.data.entities


import com.google.gson.annotations.SerializedName

data class WeaponDetailResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isShadow")
    val isShadow: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("shadowPenalty")
    val shadowPenalty: String?,
    @SerializedName("shadowBonus")
    val shadowBonus: String?
)