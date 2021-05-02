package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.WeaponApiService
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.WeaponDetailMapper
import com.free.domain.entities.WeaponDetailEntity
import com.free.domain.repositories.WeaponDetailRepository
import com.toast.comico.vn.common_jvm.functional.Either

class WeaponDetailRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val weaponApiService: WeaponApiService,
    private val weaponDetailMapper: WeaponDetailMapper
) : WeaponDetailRepository {
    override suspend fun getWeaponDetail(name: String): Either<Failure, WeaponDetailEntity> =
        Either.runSuspendWithCatchError(listOf(remoteExceptionInterceptor)) {
            val listResult = weaponApiService.getListWeaponDetail(name)
            if (listResult.size == 1) {
                val weaponDetailEntity = weaponDetailMapper.map(listResult[0])
                return@runSuspendWithCatchError Either.Success(weaponDetailEntity)
            } else {
                return@runSuspendWithCatchError Either.Fail(Failure.ConnectError)
            }
        }
}