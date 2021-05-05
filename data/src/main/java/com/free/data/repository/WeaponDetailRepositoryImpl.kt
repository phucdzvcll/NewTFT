package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.WeaponApiService
import com.free.data.entities.RecipeResponse
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.RecipeMapper
import com.free.data.map.WeaponDetailMapper
import com.free.domain.entities.RecipeEntity
import com.free.domain.entities.WeaponDetailEntity
import com.free.domain.repositories.WeaponDetailRepository
import com.free.common_jvm.functional.Either

class WeaponDetailRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val weaponApiService: WeaponApiService,
    private val recipeMapper: RecipeMapper,
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

    override suspend fun getListRecipeById(ids: List<Int>): Either<Failure, List<RecipeEntity>> =
        Either.runSuspendWithCatchError(listOf(remoteExceptionInterceptor)) {
            val listResult = mutableListOf<RecipeResponse>()
            ids.forEach {
                listResult.add(
                    weaponApiService.getRecipeResponse(it)
                )
            }
            val listRecipeEntity = recipeMapper.mapList(listResult)
            return@runSuspendWithCatchError Either.Success(listRecipeEntity)
        }
}