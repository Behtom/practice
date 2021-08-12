package com.example.practice.data.persistence

data class Result<out T>(
    val message: String? = null,
    val list: List<T>? = null,
    val e: Exception? = null
) {
}