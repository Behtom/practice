package com.example.practice.data.network

import com.example.practice.data.firebase.firestore.Product
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

data class Response(
    val querySnapshot: QuerySnapshot? = null,
    val list: List<Product>? = null,
    val message: String? = null
)