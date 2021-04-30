package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.ChampDetailApiService
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.ChampDetailMapper
import com.free.domain.entities.ChampDetailEntity
import com.free.domain.repositories.ChampDetailRepository
import com.toast.comico.vn.common_jvm.functional.Either

class ChampDetailRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val champDetailApiService: ChampDetailApiService,
    private val champDetailMapper: ChampDetailMapper
) : ChampDetailRepository {
    override suspend fun getDetailChamp(id: String): Either<Failure, ChampDetailEntity> =
        Either.runSuspendWithCatchError(listOf(remoteExceptionInterceptor)) {
            val detailChampRepository = champDetailApiService.getDetailChamp(id)
            val champDetailEntity = champDetailMapper.map(detailChampRepository)
            return@runSuspendWithCatchError Either.Success(champDetailEntity)
        }
}