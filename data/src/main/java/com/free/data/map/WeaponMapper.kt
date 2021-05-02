package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultFalse
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.WeaponResponse
import com.free.domain.entities.WeaponEntity

class WeaponMapper : Mapper<WeaponResponse?, WeaponEntity>() {
    override fun map(input: WeaponResponse?): WeaponEntity {
        return WeaponEntity(
            id = input?.id.defaultZero(),
            imgUrl = mapImgUrl(input?.isShadow.defaultFalse(), input?.name.defaultEmpty()),
            name = input?.name.defaultEmpty(),
            isShadow = input?.isShadow.defaultFalse()
        )
    }

    private fun mapImgUrl(isShadow: Boolean, name: String): String {
        val param = name.replace(" ", "").replace("'", "")
        return if (isShadow) {
            "https://rerollcdn.com/items/Shadow/$param.png"
        } else {
            "https://rerollcdn.com/items/$param.png"
        }
    }
}