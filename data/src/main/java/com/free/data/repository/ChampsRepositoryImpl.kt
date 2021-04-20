package com.free.data.repository

import com.free.common_jvm.exception.Failure
import com.free.data.exception_interceptor.RemoteExceptionInterceptor
import com.free.domain.entities.ListChampEntity
import com.free.domain.repositories.ChampsRepository
import com.toast.comico.vn.common_jvm.functional.Either

class ChampsRepositoryImpl(private val remoteExceptionInterceptor: RemoteExceptionInterceptor) :
    ChampsRepository {
    override suspend fun getListChamp(): Either<Failure, ListChampEntity> =
        Either.runSuspendWithCatchError(
            listOf(remoteExceptionInterceptor)
        ) {
            return@runSuspendWithCatchError Either.Success(
                ListChampEntity(
                    listOf(
                        ListChampEntity.Champ(
                            1,
                            "Phuc",
                            "sdfsdf",
                            1
                        )
                    )
                )
            )
        }
}