package com.myd.sccasestudy.core

import com.myd.sccasestudy.core.model.Result

abstract class BaseUseCase<out T, in Params> {
    abstract suspend fun execute(params: Params): Result<T>
}