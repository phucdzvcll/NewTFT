package com.free.domain.repositories

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.ChampDetailEntity
import com.toast.comico.vn.common_jvm.functional.Either

interface ChampDetailRepository {

    suspend fun getDetailChamp(id: String): Either<Failure, ChampDetailEntity>
}