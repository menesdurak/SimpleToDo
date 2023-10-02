package com.menesdurak.simpletodo.data

sealed class Response<out T : Any> {
    object Loading : Response<Nothing>()
    data class Success<out T : Any>(val result: T?) : Response<T>()
    data class Error(val exception : Exception) : Response<Nothing>()
}