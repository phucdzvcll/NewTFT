package com.free.domain.usecases.weapon_detail

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.RecipeEntity
import com.free.domain.repositories.WeaponDetailRepository
import com.free.domain.usecases.base.UseCase
import com.free.domain.usecases.base.UseCaseParams
import com.toast.comico.vn.common_jvm.functional.Either

class GetListRecipeUseCase(private val weaponDetailRepository: WeaponDetailRepository) :
    UseCase<GetListRecipeUseCase.GetListRecipeUseCaseParam, List<RecipeEntity>>() {
    data class GetListRecipeUseCaseParam(val ids: List<Int>) : UseCaseParams

    override suspend fun executeInternal(params: GetListRecipeUseCaseParam): Either<Failure, List<RecipeEntity>> {
        return weaponDetailRepository.getListRecipeById(params.ids)
    }
}