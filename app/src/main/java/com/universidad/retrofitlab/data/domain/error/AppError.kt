package com.universidad.retrofitlab.domain.error

import retrofit2.HttpException
import java.io.IOException

sealed class AppError {
    data class Network(val message: String) : AppError()
    data class Unauthorized(val code: Int) : AppError()
    data class NotFound(val resource: String) : AppError()
    data class Server(val code: Int) : AppError()
    data class Unknown(val throwable: Throwable) : AppError()
}

fun Throwable.toAppError(): AppError = when (this) {
    is IOException -> AppError.Network(message ?: "Sin conexión")
    is HttpException -> when (code()) {
        401, 403 -> AppError.Unauthorized(code())
        404 -> AppError.NotFound("Post")
        in 500..599 -> AppError.Server(code())
        else -> AppError.Unknown(this)
    }
    else -> AppError.Unknown(this)
}

fun AppError.toMessage(): String = when (this) {
    is AppError.Network      -> "Sin conexión a internet. Verificar la red."
    is AppError.Unauthorized -> "Sesión no autorizada ($code)."
    is AppError.NotFound     -> "$resource no encontrado."
    is AppError.Server       -> "Error del servidor ($code). Intentar más tarde."
    is AppError.Unknown      -> "Error inesperado: ${throwable.message}"
}