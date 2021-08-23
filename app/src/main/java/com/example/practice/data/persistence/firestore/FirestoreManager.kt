package com.example.practice.data.persistence.firestore

import com.example.practice.data.firebase.authentication.email_auth.IEmailAuthManager
import com.example.practice.data.persistence.IDataManager
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirestoreManager @Inject constructor(private val auth: IEmailAuthManager): IDataManager {

    private val db = Firebase.firestore

    override suspend fun getAllProducts(): Task<QuerySnapshot> {
        return withContext(Dispatchers.IO){ db.collection("products").get() }
    }

    override suspend fun getShoppingCart(): Task<DocumentSnapshot> {
        return withContext(Dispatchers.IO) { db.collection("shopping_cart").document(auth.getUserId()).get() }
    }

    override suspend fun saveShoppingCart(data: Any): Task<Void> {
        return db.collection("shopping_cart").document(auth.getUserId()).set(data)
    }
}