package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.ChampDetailApiService
import com.free.data.entities.ChampionResponse
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.data.map.ChampDetailMapper
import com.free.data.map.ChampOfTraitDetailMapper
import com.free.data.map.TraitsDetailMapper
import com.free.domain.entities.ChampDetailEntity
import com.free.domain.entities.TraitDetailEntity
import com.free.domain.repositories.ChampDetailRepository
import com.toast.comico.vn.common_jvm.functional.Either

class ChampDetailRepositoryImpl(
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val champDetailApiService: ChampDetailApiService,
    private val traitsDetailMapper: TraitsDetailMapper,
    private val champOfTraitDetailMapper: ChampOfTraitDetailMapper,
    private val champDetailMapper: ChampDetailMapper
) : ChampDetailRepository {
    override suspend fun getDetailChamp(id: String): Either<Failure, ChampDetailEntity> =
        Either.runSuspendWithCatchError(listOf(remoteExceptionInterceptor)) {
            val detailChampRepository = champDetailApiService.getDetailChamp(id)
            val champDetailEntity = champDetailMapper.map(detailChampRepository)
            champDetailEntity
            return@runSuspendWithCatchError Either.Success(champDetailEntity)
        }

    override suspend fun getTraitDetail(traits: List<String>): Either<Failure, List<TraitDetailEntity>> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            val listTraitsDetailResponse = champDetailApiService.getTraitDetail(traits)
            val traitDetailEntity = traitsDetailMapper.mapList(listTraitsDetailResponse)
            val listTraitDetailEntity = mutableListOf<TraitDetailEntity>()
            traits.forEach { trait ->
                val champResponse = champDetailApiService.getChampsByTrait(trait)
                traitDetailEntity.forEach {
                    if (it.name == trait) {
                        listTraitDetailEntity.add(
                            it.copy(
                                listChamps = champOfTraitDetailMapper.mapList(
                                    champResponse
                                )
                            )
                        )
                    }
                }

            }
            return@runSuspendWithCatchError Either.Success(listTraitDetailEntity)
        }
}