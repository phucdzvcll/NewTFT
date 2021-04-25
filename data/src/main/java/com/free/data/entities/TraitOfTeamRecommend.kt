package com.free.data.entities


import com.google.gson.annotations.SerializedName

 data class TraitOfTeamRecommend(
        @SerializedName("description")
        val description: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("sets")
        val sets: List<Set>?,
        @SerializedName("type")
        val type: String?
    ) {
        data class Set(
            @SerializedName("max")
            val max: Int?,
            @SerializedName("min")
            val min: Int?,
            @SerializedName("style")
            val style: String?
        )
    }