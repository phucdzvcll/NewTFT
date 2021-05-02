package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.data.entities.TraitOfTeamRecommendResponse
import com.free.domain.entities.TeamsRecommendEntity
import java.util.*

class TraitsListMapper {

    fun map(
        listTraitsResponse: List<TraitOfTeamRecommendResponse>,
        list: List<String>
    ): MutableList<TeamsRecommendEntity.Trait> {
        val listTraitsEntity = mutableListOf<TeamsRecommendEntity.Trait>()
        val grouping = list.groupingBy { it }.eachCount()
        listTraitsResponse.forEach {
            val style = setStyle(it, grouping[it.name].defaultZero())
            listTraitsEntity.add(
                TeamsRecommendEntity.Trait(
                    name = if (style != "none") {
                        it.name.defaultEmpty()
                    } else {
                        ""
                    },
                    amountTraits = grouping[it.name].defaultZero(),
                    style = style,
                    imgUrl = "https://rerollcdn.com/icons/" + it.name?.toLowerCase(Locale.ROOT) + ".png"
                )
            )
        }
        listTraitsEntity.sortByDescending { it.amountTraits }
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