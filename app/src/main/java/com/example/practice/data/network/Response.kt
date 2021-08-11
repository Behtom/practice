package com.example.practice.data.network

import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

data class Response(
    val querySnapshot: QuerySnapshot? = null,
    val message: String? = null
)