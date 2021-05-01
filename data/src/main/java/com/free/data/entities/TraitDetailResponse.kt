package com.free.data.entities


import com.google.gson.annotations.SerializedName

data class TraitDetailResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sets")
    val sets: List<Set>?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("innate")
    val innate: String?
) {
    data class Set(
        @SerializedName("min")
        val min: Int?,
        @SerializedName("style")
        val style: String?,
        @SerializedName("active")
        val active: String?
    )
}