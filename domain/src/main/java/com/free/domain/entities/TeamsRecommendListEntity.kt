package com.free.domain.entities

data class TeamsRecommendListEntity(
    val listTeams: List<TeamsRecommend>
) {
    data class TeamsRecommend(
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
}