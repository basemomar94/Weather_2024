package com.bassem.core.entity

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Fail(val errorTypes: ErrorTypes) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
}




