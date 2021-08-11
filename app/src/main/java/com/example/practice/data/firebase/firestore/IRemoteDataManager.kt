package com.example.practice.data.firebase.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface IRemoteDataManager {
    suspend fun getAllProducts(): Task<QuerySnapshot>
}