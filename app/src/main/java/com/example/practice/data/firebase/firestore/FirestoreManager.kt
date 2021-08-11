package com.example.practice.data.firebase.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirestoreManager @Inject constructor(): IRemoteDataManager {

    private val db = Firebase.firestore

    override suspend fun getAllProducts(): Task<QuerySnapshot> {
        return withContext(Dispatchers.IO){ db.collection("products").get() }
    }
}