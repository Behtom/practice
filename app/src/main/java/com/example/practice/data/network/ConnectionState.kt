package com.example.practice.data.network

import java.lang.Exception

sealed class ConnectionState<out T> {
    data class Success<out T>(val response: T): ConnectionState<T>()
    data class Error<out T>(val response: T, val e: Exception): ConnectionState<T>()
}
