package com.free.data.entities

import com.google.gson.annotations.SerializedName

class ChampionsListResponse : ArrayList<ChampionsListResponse.Champion?>() {
    data class Champion(
        @SerializedName("championId")
        val championId: String?,
        @SerializedName("cost")
        val cost: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("imgUrl")
        val imgUrl: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("traits")
        val traits: List<String>?
    )
}

