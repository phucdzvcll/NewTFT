package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.data.entities.TraitOfTeamRecommendResponse
import com.free.domain.entities.TeamsRecommendEntity

class TraitsListMapper {

    fun map(
        listTraitsResponse: List<TraitOfTeamRecommendResponse>,
        list: List<String>
    ): MutableList<TeamsRecommendEntity.Trait> {
        val listTraitsEntity = mutableListOf<TeamsRecommendEntity.Trait>()
        val grouping = list.groupingBy { it }.eachCount()
        listTraitsResponse.forEach {
            listTraitsEntity.add(
                TeamsRecommendEntity.Trait(
                    name = it.name.defaultEmpty(),
                    amountTraits = grouping[it.name].defaultZero(),
                    style = setStyle(it, grouping[it.name].defaultZero())
                )
            )
        }
        return listTraitsEntity
    }

    private fun setStyle(
        traitOfTeamRecommendResponse: TraitOfTeamRecommendResponse,
        amount: Int
    ): String {
        var style = "none"
        traitOfTeamRecommendResponse.sets?.forEach {
            if (it.max != null) {
                if (amount >= it.min.defaultZero() && amount <= it.max.defaultZero()) {
                    style = it.style.defaultEmpty()
                }
            } else {
                if (amount >= it.min.defaultZero()) {
                    style = it.style.defaultEmpty()
                }
            }
        }
        return style
    }
}