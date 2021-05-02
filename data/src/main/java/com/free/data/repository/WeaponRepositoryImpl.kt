package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.WeaponApiService
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.WeaponMapper
import com.free.domain.entities.WeaponEntity
import com.free.domain.repositories.WeaponRepository
import com.toast.comico.vn.common_jvm.functional.Either

class WeaponRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val weaponApiService: WeaponApiService,
    private val weaponMapper: WeaponMapper
) : WeaponRepository {
    override suspend fun getListWeapon(isElement: Boolean): Either<Failure, List<WeaponEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val listResponse = weaponApiService.getListWeaponResponse(isElement)
            val listResults = weaponMapper.mapList(listResponse
                .sortedBy { it.isShadow }
                .sortedBy { it.isSpecial })
            return@runSuspendWithCatchError Either.Success(listResults)
        }
}