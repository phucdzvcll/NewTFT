package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.TraitDetailResponse
import com.free.domain.entities.TraitDetailEntity
import java.util.*

class TraitsDetailMapper : Mapper<TraitDetailResponse?, TraitDetailEntity>() {
    override fun map(input: TraitDetailResponse?): TraitDetailEntity {
        return TraitDetailEntity(
            name = input?.name.defaultEmpty(),
            listSets = mapSet(input?.sets.defaultEmpty()),
            innate = input?.innate.defaultEmpty(),
            listChamps = listOf(),
            description = input?.description.defaultEmpty(),
            imgUrl = "https://rerollcdn.com/icons/" + input?.name.defaultEmpty().toLowerCase(Locale.ROOT) + ".png"
        )
    }

    private fun mapSet(listSets: List<TraitDetailResponse.Set>?): List<TraitDetailEntity.Set> {
        val listSetsEntity = mutableListOf<TraitDetailEntity.Set>()
        listSets?.forEach {
            listSetsEntity.add(
                TraitDetailEntity.Set(
                    min = it.min.defaultZero(),
                    active = it.active.defaultEmpty(),
                    style = it.style.defaultEmpty()
                )
            )
        }
        return listSetsEntity
    }
}