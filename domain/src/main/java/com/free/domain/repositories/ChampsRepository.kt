package com.free.domain.repositories

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.ChampsEntity
import com.toast.comico.vn.common_jvm.functional.Either

interface ChampsRepository {
    suspend fun getListChamp(): Either<Failure, List<ChampsEntity>>
}