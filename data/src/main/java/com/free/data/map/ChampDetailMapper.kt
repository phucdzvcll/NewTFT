package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.ChampDetailResponse
import com.free.domain.entities.ChampDetailEntity

class ChampDetailMapper:Mapper<ChampDetailResponse?,ChampDetailEntity>() {
    override fun map(input: ChampDetailResponse?): ChampDetailEntity {
        return ChampDetailEntity(
            id = input?.id.defaultEmpty(),
            cost = input?.cost.defaultZero(),
            items = input?.items.defaultEmpty(),
            name = input?.name.defaultEmpty(),
            traits = input?.traits.defaultEmpty()
        )
    }
}