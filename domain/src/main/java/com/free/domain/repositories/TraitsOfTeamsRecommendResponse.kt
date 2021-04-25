package com.free.domain.repositories

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.TraitsOfTeamRecommendEntity
import com.toast.comico.vn.common_jvm.functional.Either

interface TraitsOfTeamsRecommendResponse {
    suspend fun getTraitsOfTeamsRecommend(listTraitsName: List<String>): Either<Failure, List<TraitsOfTeamRecommendEntity>>
}