package com.inaki.fruitmvvmapp.utils

/**
 * This sealed class will be a wrapper for the UIState like loading, success or error
 *
 * We are providing
 */
sealed class UIState<out T, out E>(
    val state: ResponseState,
    val data: T? = null,
    val error: E? = null
) {
    class Loading<T> : UIState<T, Throwable>(ResponseState.LOADING)
    class Success<T>(data: T): UIState<T, Throwable>(ResponseState.SUCCESS, data = data)
    class Error<T>(error: Throwable): UIState<T, Throwable>(ResponseState.ERROR, error = error)
}

enum class ResponseState {
    LOADING,
    SUCCESS,
    ERROR;
}