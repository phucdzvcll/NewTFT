package com.free.data.entities


import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)