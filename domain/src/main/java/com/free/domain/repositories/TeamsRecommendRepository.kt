package com.free.domain.repositories

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.TeamsRecommendListEntity
import com.toast.comico.vn.common_jvm.functional.Either

interface TeamsRecommendRepository {
    suspend fun getListTeamsRecommend(): Either<Failure, TeamsRecommendListEntity>
}