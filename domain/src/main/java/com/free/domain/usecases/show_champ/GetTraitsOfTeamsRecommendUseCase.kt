package com.free.domain.usecases.show_champ

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.TraitsOfTeamRecommendEntity
import com.free.domain.repositories.TraitsOfTeamsRecommendResponse
import com.free.domain.usecases.base.UseCase
import com.free.domain.usecases.base.UseCaseParams
import com.toast.comico.vn.common_jvm.functional.Either

class GetTraitsOfTeamsRecommendUseCase(private val traitsOfTeamsRecommendResponse: TraitsOfTeamsRecommendResponse) :
    UseCase<GetTraitsOfTeamsRecommendUseCase.GetTraitsOfTeamsRecommendUseCaseParam, List<TraitsOfTeamRecommendEntity>>() {

    data class GetTraitsOfTeamsRecommendUseCaseParam(val listTraitsName: List<String>) :
        UseCaseParams

    override suspend fun executeInternal(params: GetTraitsOfTeamsRecommendUseCaseParam): Either<Failure, List<TraitsOfTeamRecommendEntity>> {
        return traitsOfTeamsRecommendResponse.getTraitsOfTeamsRecommend(params.listTraitsName)
    }
}