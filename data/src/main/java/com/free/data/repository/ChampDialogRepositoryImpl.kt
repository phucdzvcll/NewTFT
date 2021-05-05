package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.ChampDialogApiService
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.ChampDialogMapper
import com.free.domain.entities.ChampDialogEntity
import com.free.domain.repositories.ChampDialogRepository
import com.free.common_jvm.functional.Either

class ChampDialogRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val champDialogApiService: ChampDialogApiService,
    private val champDialogMapper: ChampDialogMapper
) : ChampDialogRepository {
    override suspend fun getChampDialog(id: String): Either<Failure, ChampDialogEntity> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val champDialogResponse = champDialogApiService.getChampsList(id)
            return@runSuspendWithCatchError Either.Success(champDialogMapper.map(champDialogResponse))
        }
}