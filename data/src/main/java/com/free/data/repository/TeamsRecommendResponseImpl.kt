package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.RecommendTeamsApiService
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.TeamRecommendListMapper
import com.free.domain.entities.TeamsRecommendListEntity
import com.free.domain.repositories.TeamsRecommendRepository
import com.toast.comico.vn.common_jvm.functional.Either

class TeamsRecommendResponseImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val recommendTeamsApiService: RecommendTeamsApiService,
    private val teamsRecommendListMapper: TeamRecommendListMapper
) : TeamsRecommendRepository {
    override suspend fun getListTeamsRecommend(): Either<Failure, TeamsRecommendListEntity> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val teamsRecommendListResponse = recommendTeamsApiService.getTeamsRecommend()
            val teamsRecommendListEntity = teamsRecommendListMapper.map(teamsRecommendListResponse)
            return@runSuspendWithCatchError Either.Success(teamsRecommendListEntity)
        }

}