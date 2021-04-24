package com.free.common_jvm.extension

fun String?.createImgUrl(): String {
    val url = "https://rerollcdn.com/characters/Skin/4.5/"
    return url + reName(this.defaultEmpty()) + ".png"
}

private fun reName(name: String): String {
    return if (name == "Cho'Gath") {
        "Chogath"
    } else if (name == "Nunu & Willump") {
        "Nunu"
    } else {
        name.replace(" ", "")
    }
}