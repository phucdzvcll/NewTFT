package com.free.domain.repositories

import com.free.common_jvm.exception.Failure
import com.free.domain.entities.ChampDialogEntity
import com.free.common_jvm.functional.Either

interface ChampDialogRepository {
    suspend fun getChampDialog(id: String): Either<Failure, ChampDialogEntity>
}