package com.example.domain.extensions

import com.example.domain.entities.CustomError
import com.example.domain.entities.ErrorThrowable
import com.example.domain.entities.NetworkError
import com.example.domain.entities.UnknownError
import java.net.UnknownHostException

fun CustomError.toThrowable(): ErrorThrowable {
    return ErrorThrowable(code, message, cause)
}

fun Throwable.toError(): CustomError {
    return when (this) {
        is ErrorThrowable -> this.toError()
        is UnknownHostException -> NetworkError(message = this.message, cause = this)
        else -> UnknownError(message = this.message, cause = this)
    }
}
