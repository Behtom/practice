package com.example.practice.data.network

import com.google.firebase.firestore.QuerySnapshot

data class Response<out T>(
    val querySnapshot: QuerySnapshot? = null,
    val list: List<T>? = null,
    val message: String? = null
)