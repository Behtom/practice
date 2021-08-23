package com.example.practice.data.persistence

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface IDataManager {
    suspend fun getAllProducts(): Task<QuerySnapshot>
    suspend fun getShoppingCart(): Task<DocumentSnapshot>
    suspend fun saveShoppingCart(data: Any): Task<Void>
}