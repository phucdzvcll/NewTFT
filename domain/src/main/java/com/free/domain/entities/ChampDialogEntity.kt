package com.free.domain.entities

data class ChampDialogEntity(
    val name: String,
    val cost: Int,
    val id: String,
    val traits: List<Trait>,
    val items: List<String>,
    val imgUrl: String,
    val coverUrl: String
) {
    data class Trait(
        val name: String,
        val imgUrl: String
    )
}