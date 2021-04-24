package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.TeamsListResponse
import com.free.domain.entities.TeamsRecommendListEntity

class TeamRecommendListMapper() : Mapper<TeamsListResponse, TeamsRecommendListEntity>() {
    override fun map(input: TeamsListResponse): TeamsRecommendListEntity {
        val itemList = input.defaultEmpty().map { teamItem ->
            TeamsRecommendListEntity.TeamsRecommend(
                id = teamItem?.id.defaultZero(),
                rank = teamItem?.rank.defaultEmpty(),
                listChamps = champListMapper(teamItem?.champions.defaultEmpty())
            )
        }
        return TeamsRecommendListEntity(itemList)
    }

    private fun champListMapper(championsRepose: List<TeamsListResponse.TeamItem.Champion>): List<TeamsRecommendListEntity.TeamsRecommend.Champ> {

        val listChamp: MutableList<TeamsRecommendListEntity.TeamsRecommend.Champ> = mutableListOf()
        championsRepose.forEach { champion ->
            listChamp.add(
                TeamsRecommendListEntity.TeamsRecommend.Champ(
                    id = champion.id.defaultEmpty(),
                    name = champion.id.defaultEmpty(),
                    cost = champion.cost.defaultZero(),
                    traits = champion.traits.defaultEmpty()

                )
            )
        }
        return listChamp
    }
}