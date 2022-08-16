package com.aks.nasapictures.ui.utils

sealed class StatusHandler<out T> {
    data class Success<out T>(val data: T) : StatusHandler<T>()
    data class Error(
        val errorResponse:Exception
    ) : StatusHandler<Nothing>()
}