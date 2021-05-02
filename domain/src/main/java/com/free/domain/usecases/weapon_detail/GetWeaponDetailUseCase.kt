package com.free.domain.usecases.weapon_detail

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.WeaponDetailEntity
import com.free.domain.entities.WeaponEntity
import com.free.domain.repositories.WeaponDetailRepository
import com.free.domain.usecases.base.UseCase
import com.free.domain.usecases.base.UseCaseParams
import com.toast.comico.vn.common_jvm.functional.Either

class GetWeaponDetailUseCase(private val weaponDetailRepository: WeaponDetailRepository) :
    UseCase<GetWeaponDetailUseCase.WeaponDetailUseCaseParam, WeaponDetailEntity>() {

    data class WeaponDetailUseCaseParam(val name: String) : UseCaseParams

    override suspend fun executeInternal(params: WeaponDetailUseCaseParam): Either<Failure, WeaponDetailEntity> {
        return weaponDetailRepository.getWeaponDetail(params.name)
    }
}