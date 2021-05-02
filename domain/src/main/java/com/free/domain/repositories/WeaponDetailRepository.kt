package com.free.domain.repositories

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.RecipeEntity
import com.free.domain.entities.WeaponDetailEntity
import com.free.domain.entities.WeaponEntity
import com.toast.comico.vn.common_jvm.functional.Either

interface WeaponDetailRepository {
    suspend fun getWeaponDetail(name: String): Either<Failure, WeaponDetailEntity>

    suspend fun getListRecipeById(ids: List<Int>): Either<Failure, List<RecipeEntity>>
}