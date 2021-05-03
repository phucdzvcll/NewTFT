package com.free.domain.repositories

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.ChampOfTeamBuilderEntity
import com.toast.comico.vn.common_jvm.functional.Either

interface TeamBuilderRepository {
    suspend fun getListChamp(): Either<Failure, List<ChampOfTeamBuilderEntity>>
}