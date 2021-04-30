package com.free.domain.usecases.base

import com.free.common_jvm.exception.Failure
import com.toast.comico.vn.common_jvm.functional.Either

abstract class  UseCase<Params : UseCaseParams, Result>() {

    suspend fun execute(params: Params): Either<Failure, Result> {
        return try {
            executeInternal(params)
        } catch (e: Exception) {
            Either.Fail(Failure.UnCatchError(e))
        }
    }

    protected abstract suspend fun executeInternal(params: Params): Either<Failure, Result>
}