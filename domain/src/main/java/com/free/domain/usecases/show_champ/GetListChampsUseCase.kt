package com.free.domain.usecases.show_champ

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.ListChampEntity
import com.free.domain.repositories.ChampsRepository
import com.free.domain.usecases.base.UseCase
import com.free.domain.usecases.base.UseCaseParams
import com.toast.comico.vn.common_jvm.functional.Either

class GetListChampsUseCase(private val champsRepository: ChampsRepository) :
    UseCase<UseCaseParams.Empty, ListChampEntity>() {
    override suspend fun executeInternal(params: UseCaseParams.Empty): Either<Failure, ListChampEntity> {
        return champsRepository.getListChamp()
    }
}