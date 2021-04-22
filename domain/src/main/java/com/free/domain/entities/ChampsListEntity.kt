package com.free.domain.entities

data class ChampsListEntity(val listChamps: List<Champ>) {
    data class Champ(
        val id: String,
        val name: String,
        val cost: Int
    )
}