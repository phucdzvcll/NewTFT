package com.free.domain.entities

data class ChampDetailEntity(
    val id: String,
    val name: String,
    val cost: Int,
    val items: List<String>,
    val traits: List<String>
)