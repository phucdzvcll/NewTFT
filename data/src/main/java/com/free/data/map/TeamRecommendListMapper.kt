package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.TeamsResponse
import com.free.domain.entities.TeamsRecommendEntity

class TeamRecommendListMapper() : Mapper<TeamsResponse?, TeamsRecommendEntity>() {
    override fun map(input: TeamsResponse?): TeamsRecommendEntity {
        return TeamsRecommendEntity(
            id = input?.id.defaultZero(),
            rank = input?.rank.defaultEmpty(),
            listChamps = champListMapper(input?.champions.defaultEmpty())
        )

    }

    private fun champListMapper(championsRepose: List<TeamsResponse.Champion>): List<TeamsRecommendEntity.Champ> {

        val champ: MutableList<TeamsRecommendEntity.Champ> = mutableListOf()
        championsRepose.forEach { champion ->
            champ.add(
                TeamsRecommendEntity.Champ(
                    id = champion.id.defaultEmpty(),
                    name = champion.id.defaultEmpty(),
                    cost = champion.cost.defaultZero(),
                    traits = champion.traits.defaultEmpty()

                )
            )
        }
        return champ
    }
}