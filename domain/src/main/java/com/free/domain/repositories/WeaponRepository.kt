package com.free.domain.repositories

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.WeaponEntity
import com.toast.comico.vn.common_jvm.functional.Either

interface WeaponRepository {
    suspend fun getListWeapon(isElement: Boolean): Either<Failure, List<WeaponEntity>>
}