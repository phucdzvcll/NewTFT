package com.free.domain.usecases.champ_detail

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.ChampDetailEntity
import com.free.domain.repositories.ChampDetailRepository
import com.free.domain.usecases.base.UseCase
import com.free.domain.usecases.base.UseCaseParams
import com.free.common_jvm.functional.Either

class GetChampDetailUseCase(private val champDetailRepository: ChampDetailRepository) :
    UseCase<GetChampDetailUseCase.GetChampDetailUseCaseParam, ChampDetailEntity>() {

    data class GetChampDetailUseCaseParam(val id: String) : UseCaseParams

    override suspend fun executeInternal(params: GetChampDetailUseCaseParam): Either<Failure, ChampDetailEntity> {
        return champDetailRepository.getDetailChamp(params.id)
    }
}