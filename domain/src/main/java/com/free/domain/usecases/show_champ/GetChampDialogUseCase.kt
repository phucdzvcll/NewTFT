package com.free.domain.usecases.show_champ

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.ChampDialogEntity
import com.free.domain.repositories.ChampDialogRepository
import com.free.domain.usecases.base.UseCase
import com.free.domain.usecases.base.UseCaseParams
import com.toast.comico.vn.common_jvm.functional.Either

class GetChampDialogUseCase(private val champDialogRepository: ChampDialogRepository) :
    UseCase<GetChampDetailUseCase.GetChampDetailUseCaseParam, ChampDialogEntity>() {


    data class GetChampDialogUseCaseParams(val id: String) : UseCaseParams

    override suspend fun executeInternal(params: GetChampDetailUseCase.GetChampDetailUseCaseParam): Either<Failure, ChampDialogEntity> {
        return champDialogRepository.getChampDialog(params.id)
    }
}