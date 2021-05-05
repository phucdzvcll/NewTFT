package com.free.domain.usecases.weapon

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.WeaponEntity
import com.free.domain.repositories.WeaponRepository
import com.free.domain.usecases.base.UseCase
import com.free.domain.usecases.base.UseCaseParams
import com.free.common_jvm.functional.Either

class GetListWeaponUseCase(private val weaponRepository: WeaponRepository) :
    UseCase<GetListWeaponUseCase.GetListWeaponUseCaseParam, List<WeaponEntity>>() {
    override suspend fun executeInternal(params: GetListWeaponUseCaseParam): Either<Failure, List<WeaponEntity>> {
        return weaponRepository.getListWeapon(params.isElement)
    }

    data class GetListWeaponUseCaseParam(val isElement: Boolean) : UseCaseParams
}