package com.free.domain.usecases.team_recommend

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.TeamsRecommendEntity
import com.free.domain.repositories.TeamsRecommendRepository
import com.free.domain.usecases.base.UseCase
import com.free.domain.usecases.base.UseCaseParams
import com.free.common_jvm.functional.Either

class GetListTeamsRecommendUseCase(val teamsRecommendRepository: TeamsRecommendRepository) :
    UseCase<UseCaseParams.Empty, List<TeamsRecommendEntity>>() {
    override suspend fun executeInternal(params: UseCaseParams.Empty): Either<Failure, List<TeamsRecommendEntity>> {
        return teamsRecommendRepository.getListTeamsRecommend()
    }
}