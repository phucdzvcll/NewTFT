package com.free.domain.repositories

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.TeamsRecommendEntity
import com.free.common_jvm.functional.Either

interface TeamsRecommendRepository {
    suspend fun getListTeamsRecommend(): Either<Failure, List<TeamsRecommendEntity>>
}