package com.myd.sccasestudy.core.model

sealed class Result<out Payload> {
    data class Success<Payload>(val payload: Payload?) : Result<Payload>()
    data class Error(val error: String?) : Result<Nothing>()
}