package com.free.data.entities

import com.google.gson.annotations.SerializedName

data class TeamsResponse(
    @SerializedName("champions")
    val champions: List<Champion>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("rank")
    val rank: String?
) {
    data class Champion(
        @SerializedName("cost")
        val cost: Int?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("traits")
        val traits: List<String>?,
        @SerializedName("isThreeStars")
        val isThreeStars: Boolean?
    )
}
