package com.free.data.entities

import com.google.gson.annotations.SerializedName

data class ChampDialogResponse(
    @SerializedName("cost")
    val cost: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("traits")
    val traits: List<String>?,
    @SerializedName("items")
    val items: List<String>?
)

