package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.ChampionsListResponse

import com.free.domain.entities.ChampsListEntity

class ChampsListMapper : Mapper<ChampionsListResponse, ChampsListEntity>() {
    override fun map(input: ChampionsListResponse): ChampsListEntity {
        val champs = input.defaultEmpty().map { champion ->
            ChampsListEntity.Champ(
                id = champion?.id.defaultZero(),
                name = champion?.name.defaultEmpty(),
                cost = champion?.cost.defaultZero(),
                imgUrl = champion?.imgUrl.defaultEmpty()
            )
        }
        return ChampsListEntity(champs)
    }

}