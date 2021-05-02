package com.free.domain.entities

data class ChampDetailEntity(
    val id: String,
    val name: String,
    val cost: Int,
    val items: List<Item>,
    val imgUrl: String,
    val coverImgUrl: String,
    val traits: List<String>
) {
    data class Item(
        val name: String,
        val imgUrl: String
    )
}