package com.free.data.map

import com.free.common_jvm.extension.createImgUrl
import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.ChampionResponse

import com.free.domain.entities.ChampsEntity

class ChampsListMapper : Mapper<ChampionResponse?, ChampsEntity>() {
    override fun map(input: ChampionResponse?): ChampsEntity {
        return ChampsEntity(
            id = input?.id.defaultEmpty(),
            name = input?.name.defaultEmpty(),
            imgUrl = input?.name.createImgUrl(),
            cost = input?.cost.defaultZero()
        )
    }


}