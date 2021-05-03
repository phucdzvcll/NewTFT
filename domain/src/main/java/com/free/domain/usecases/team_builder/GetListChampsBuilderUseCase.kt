package com.free.domain.usecases.team_builder

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.ChampOfTeamBuilderEntity
import com.free.domain.repositories.TeamBuilderRepository
import com.free.domain.usecases.base.UseCase
import com.free.domain.usecases.base.UseCaseParams
import com.toast.comico.vn.common_jvm.functional.Either

class GetListChampsBuilderUseCase(private val teamBuilderRepository: TeamBuilderRepository) :
    UseCase<UseCaseParams.Empty, List<ChampOfTeamBuilderEntity>>() {
    override suspend fun executeInternal(params: UseCaseParams.Empty): Either<Failure, List<ChampOfTeamBuilderEntity>> {
        return teamBuilderRepository.getListChamp()
    }
}