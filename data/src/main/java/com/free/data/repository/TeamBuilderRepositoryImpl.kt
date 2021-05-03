package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.TeamBuilderApiService
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.ChampBuilderMapper
import com.free.domain.entities.ChampOfTeamBuilderEntity
import com.free.domain.repositories.TeamBuilderRepository
import com.toast.comico.vn.common_jvm.functional.Either

class TeamBuilderRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val champBuilderMapper: ChampBuilderMapper,
    private val teamBuilderApiService: TeamBuilderApiService
) : TeamBuilderRepository {
    override suspend fun getListChamp(): Either<Failure, List<ChampOfTeamBuilderEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val listResult = teamBuilderApiService.getListChampsBuilder()
            val listChampOfTeamBuilderEntity = champBuilderMapper.mapList(listResult)
            return@runSuspendWithCatchError Either.Success(listChampOfTeamBuilderEntity.sortedBy { it.cost })
        }
}