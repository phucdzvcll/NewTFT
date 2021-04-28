package com.free.domain.entities

data class TeamsRecommendEntity(
    val id: Int,
    val rank: String,
    val listChamps: List<Champ>,
    val listTraits: List<Trait>
) {
    data class Champ(
        val isThreeStars: Boolean,
        val name: String,
        val id: String,
        val cost: Int,
        val traits: List<String>
    )

    data class Trait(
        val name: String,
        val style: String,
        val amountTraits: Int
    )
}