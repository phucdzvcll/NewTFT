package com.free.domain.entities

data class TraitDetailEntity(
    val name: String,
    val innate: String,
    val description: String,
    val listChamps: List<Champion>,
    val listSets: List<Set>
) {
    data class Champion(
        val id: String,
        val name: String,
        val cost: Int
    )

    data class Set(
        val min: Int,
        val style: String,
        val active: String
    )
}