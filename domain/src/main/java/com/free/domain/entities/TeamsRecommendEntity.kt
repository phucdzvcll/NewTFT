package com.free.domain.entities

    data class TeamsRecommendEntity(
        val id: Int,
        val rank: String,
        val listChamps: List<Champ>
    ) {
        data class Champ(
            val name: String,
            val id: String,
            val cost: Int,
            val traits: List<String>
        )
    }