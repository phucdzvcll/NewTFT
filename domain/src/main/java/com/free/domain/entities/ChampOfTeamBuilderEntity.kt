package com.free.domain.entities

data class ChampOfTeamBuilderEntity(
    val name: String,
    val cost: Int,
    val id: String,
    val traits: List<Trait>,
    val imgUrl: String
) {
    data class Trait(
        val name: String,
        val imgUrl: String
    )
}