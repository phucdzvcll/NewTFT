package com.free.domain.usecases.show_champ

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.TraitDetailEntity
import com.free.domain.repositories.ChampDetailRepository
import com.free.domain.usecases.base.UseCase
import com.free.domain.usecases.base.UseCaseParams
import com.toast.comico.vn.common_jvm.functional.Either

class GetTraitsDetailUseCase(private val champDetailRepository: ChampDetailRepository) :
    UseCase<GetTraitsDetailUseCase.GetTraitsDetailUseCaseParams, List<TraitDetailEntity>>() {
    data class GetTraitsDetailUseCaseParams(val traits: List<String>) : UseCaseParams

    override suspend fun executeInternal(params: GetTraitsDetailUseCaseParams): Either<Failure, List<TraitDetailEntity>> {
        return champDetailRepository.getTraitDetail(params.traits)
    }
}