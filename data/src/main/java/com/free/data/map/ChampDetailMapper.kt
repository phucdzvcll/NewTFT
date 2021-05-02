package com.free.data.map

import com.free.common_jvm.extension.createImgUrl
import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.ChampDetailResponse
import com.free.domain.entities.ChampDetailEntity

class ChampDetailMapper : Mapper<ChampDetailResponse?, ChampDetailEntity>() {
    override fun map(input: ChampDetailResponse?): ChampDetailEntity {
        return ChampDetailEntity(
            id = input?.id.defaultEmpty(),
            cost = input?.cost.defaultZero(),
            items = mapItems(input?.items),
            name = input?.name.defaultEmpty(),
            traits = input?.traits.defaultEmpty(),
            coverImgUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${input?.name}_0.jpg",
            imgUrl = input?.name.createImgUrl()
        )
    }

    private fun mapItems(list: List<String>?): List<ChampDetailEntity.Item> {
        val listItems = mutableListOf<ChampDetailEntity.Item>()
        list?.forEach {
            val name = it.replace(" ", "").replace("'", "")
            listItems.add(
                ChampDetailEntity.Item(
                    name = it,
                    imgUrl = "https://rerollcdn.com/items/$name.png"
                )
            )
        }
        return listItems
    }
}