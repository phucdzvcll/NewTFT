package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultFalse
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.WeaponDetailResponse
import com.free.domain.entities.WeaponDetailEntity

class WeaponDetailMapper : Mapper<WeaponDetailResponse?, WeaponDetailEntity>() {
    override fun map(input: WeaponDetailResponse?): WeaponDetailEntity {
        val name = input?.name.defaultEmpty().replace(" ", "").replace("'", "")
        return WeaponDetailEntity(
            id = input?.id.defaultZero(),
            name = input?.name.defaultEmpty(),
            description = input?.description.defaultEmpty(),
            shadowBonus = input?.shadowBonus.defaultEmpty(),
            shadowPenalty = input?.shadowPenalty.defaultEmpty(),
            isShadow = input?.isShadow.defaultFalse(),
            imgUrl = imgUrlMapper(input?.isShadow.defaultFalse(), name)
        )
    }

    private fun imgUrlMapper(idShadow: Boolean, name: String): String {
        return when (idShadow) {
            true -> {
                "https://rerollcdn.com/items/Shadow/$name.png"
            }
            else -> {
                "https://rerollcdn.com/items/$name.png"
            }
        }
    }
}