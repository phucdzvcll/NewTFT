package com.free.data.entities


import com.google.gson.annotations.SerializedName

data class WeaponResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isShadow")
    val isShadow: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("isSpecial")
    val isSpecial: Boolean
)