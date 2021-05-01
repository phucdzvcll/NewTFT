package com.free.data.map

import com.free.common_jvm.extension.createImgUrl
import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.ChampDialogResponse
import com.free.domain.entities.ChampDialogEntity
import java.util.*

class ChampDialogMapper : Mapper<ChampDialogResponse?, ChampDialogEntity>() {
    override fun map(input: ChampDialogResponse?): ChampDialogEntity {
        return ChampDialogEntity(
            traits = mapTraits(input?.traits.defaultEmpty()),
            name = input?.name.defaultEmpty(),
            cost = input?.cost.defaultZero(),
            items = mapItems(input?.items.defaultEmpty()),
            id = input?.id.defaultEmpty(),
            imgUrl = input?.name.createImgUrl(),
            coverUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${input?.name.defaultEmpty()}_0.jpg",
        )
    }

    private fun mapItems(list: List<String>): MutableList<String> {
        val items = mutableListOf<String>()
        list.forEach {
            items.add(
                "https://rerollcdn.com/items/$it.png"
            )
        }
        return items
    }

    private fun mapTraits(list: List<String>): MutableList<ChampDialogEntity.Trait> {
        val traits = mutableListOf<ChampDialogEntity.Trait>()
        list.forEach {
            traits.add(
                ChampDialogEntity.Trait(
                    name = it,
                    imgUrl = "https://rerollcdn.com/icons/" + it.toLowerCase(Locale.ROOT) + ".png",

                    )
            )
        }
        return traits
    }
}