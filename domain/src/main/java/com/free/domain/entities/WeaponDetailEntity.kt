package com.free.domain.entities

data class WeaponDetailEntity(
    val id: Int,
    val name: String,
    val description: String,
    val shadowBonus: String,
    val shadowPenalty: String,
    val isShadow: Boolean,
    val imgUrl: String
)