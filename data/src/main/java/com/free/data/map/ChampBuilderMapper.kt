package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.ChampBuilderResponse
import com.free.domain.entities.ChampOfTeamBuilderEntity
import java.util.*

class ChampBuilderMapper : Mapper<ChampBuilderResponse?, ChampOfTeamBuilderEntity>() {
    override fun map(input: ChampBuilderResponse?): ChampOfTeamBuilderEntity {
        return ChampOfTeamBuilderEntity(
            id = input?.id.defaultEmpty(),
            name = input?.name.defaultEmpty(),
            cost = input?.cost.defaultZero(),
            traits = mapTraits(input?.traits.defaultEmpty()),
            imgUrl = "https://rerollcdn.com/characters/Skin/5/" + input?.name.defaultEmpty()
                .replace(" ", "").replace("'", "") + ".png"
        )
    }

    private fun mapTraits(list: List<String>): MutableList<ChampOfTeamBuilderEntity.Trait> {
        val traits = mutableListOf<ChampOfTeamBuilderEntity.Trait>()
        list.forEach {
            traits.add(
                ChampOfTeamBuilderEntity.Trait(
                    name = it,
                    imgUrl = "https://rerollcdn.com/icons/" + it.toLowerCase(Locale.ROOT) + ".png",

                    )
            )
        }
        return traits
    }
}