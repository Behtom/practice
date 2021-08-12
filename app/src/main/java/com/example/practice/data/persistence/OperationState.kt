package com.example.practice.data.persistence

sealed class OperationState<out T> {
    data class Success<out T>(val state: T): OperationState<T>()
    data class Error<out T>(val state: T, val e: Exception): OperationState<T>()
}
