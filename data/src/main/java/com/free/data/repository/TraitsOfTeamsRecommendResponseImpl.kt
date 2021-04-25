package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.TraitsOfTeamRecommendService
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.domain.entities.TraitsOfTeamRecommendEntity
import com.free.domain.repositories.TraitsOfTeamsRecommendResponse
import com.toast.comico.vn.common_jvm.functional.Either

class TraitsOfTeamsRecommendResponseImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val traitsOfTeamRecommendService : TraitsOfTeamRecommendService
) : TraitsOfTeamsRecommendResponse {
    override suspend fun getTraitsOfTeamsRecommend(listTraitsName: List<String>): Either<Failure, List<TraitsOfTeamRecommendEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            //val traits = traitsOfTeamRecommendService.getTraitsOfTeamsRecommend(listTraitsName)
            return@runSuspendWithCatchError (Either.Success(listOf()))
        }
}