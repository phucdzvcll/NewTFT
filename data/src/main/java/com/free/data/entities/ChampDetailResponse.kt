package com.free.data.entities

import com.google.gson.annotations.SerializedName

data class ChampDetailResponse(
    @SerializedName("cost")
    val cost: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("items")
    val items: List<String>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("traits")
    val traits: List<String>?
)