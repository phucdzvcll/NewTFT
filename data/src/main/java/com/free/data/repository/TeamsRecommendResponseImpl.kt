package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.common_jvm.extension.defaultEmpty
import com.free.data.RecommendTeamsApiService
import com.free.data.TraitsOfTeamRecommendService
import com.free.data.entities.TeamsResponse
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.TraitsListMapper
import com.free.data.map.TeamRecommendMapper
import com.free.domain.entities.TeamsRecommendEntity
import com.free.domain.repositories.TeamsRecommendRepository
import com.toast.comico.vn.common_jvm.functional.Either

class TeamsRecommendResponseImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val recommendTeamsApiService: RecommendTeamsApiService,
    private val teamsRecommendMapper: TeamRecommendMapper,
    private val traitsOfTeamRecommendService: TraitsOfTeamRecommendService,
    private val traitsListMapper: TraitsListMapper
) : TeamsRecommendRepository {
    override suspend fun getListTeamsRecommend(): Either<Failure, List<TeamsRecommendEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val listTeamsResponse = recommendTeamsApiService.getTeamsRecommend()
            getListTeamsRecommendEntity(listTeamsResponse.distinct())
            val listTeamsRecommendEntity = getListTeamsRecommendEntity(listTeamsResponse)
            return@runSuspendWithCatchError Either.Success(listTeamsRecommendEntity)
        }

    private suspend fun getListTeamsRecommendEntity(teamResponses: List<TeamsResponse>): List<TeamsRecommendEntity> {
        val teamEntities = mutableListOf<TeamsRecommendEntity>()
        teamResponses.forEach { team ->
            val keyParams = listKeyParam(team)
            val traitEntities =
                traitsListMapper.map(
                    listTraitsResponse = traitsOfTeamRecommendService.getTraitsOfTeamsRecommend(
                        keys = keyParams.distinct()
                    ), grouping = keyParams.groupingBy { it }.eachCount()
                )
            teamEntities.add(
                teamsRecommendMapper.map(team).copy(listTraits = traitEntities)
            )
        }
        return teamEntities
    }

    private fun listKeyParam(teamsResponse: TeamsResponse): MutableList<String> {
        val listString = mutableListOf<String>()
        teamsResponse.champions?.forEach { champion ->
            listString.addAll(champion.traits.defaultEmpty())
        }
        return listString
    }


}