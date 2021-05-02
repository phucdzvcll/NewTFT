package com.free.data.map

import com.free.common_jvm.extension.createImgUrl
import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.ChampionResponse
import com.free.domain.entities.TraitDetailEntity

class ChampOfTraitDetailMapper : Mapper<ChampionResponse?, TraitDetailEntity.Champion>() {
    override fun map(input: ChampionResponse?): TraitDetailEntity.Champion {
        return TraitDetailEntity.Champion(
            name = input?.name.defaultEmpty(),
            cost = input?.cost.defaultZero(),
            id = input?.id.defaultEmpty(),
            imgUrl = input?.name.createImgUrl()
        )
    }
}