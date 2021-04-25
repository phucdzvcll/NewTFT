package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.ShowChampApiService
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.ChampsListMapper
import com.free.domain.entities.ChampsEntity
import com.free.domain.repositories.ChampsRepository
import com.toast.comico.vn.common_jvm.functional.Either

class ChampsRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val showChampApiService: ShowChampApiService,
    private val champsListMapper: ChampsListMapper
    ) : ChampsRepository {
    override suspend fun getListChamp(): Either<Failure, List<ChampsEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val champsListResponse = showChampApiService.getChampsList()
            val champsListEntity = champsListMapper.mapList(champsListResponse)
            return@runSuspendWithCatchError Either.Success(champsListEntity)
        }
}