package com.example.practice.domain.repository.home

import com.example.practice.data.firebase.firestore.FirestoreManager
import com.example.practice.data.persistence.db.AppDatabase
import com.example.practice.data.persistence.db.entities.Products
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val firestoreManager: FirestoreManager
): IHomeRepository {
    override suspend fun getAllProductsFromRemote(): Task<QuerySnapshot> {
        return withContext(Dispatchers.IO){ firestoreManager.getAllProducts() }
    }

    /*override suspend fun insertProduct(product: Products) {
        withContext(Dispatchers.IO) { db.productsDao().insert(product) }
    }

    override suspend fun insertProducts(products: List<Products>) {
        withContext(Dispatchers.IO) { db.productsDao().insert(products) }
    }*/
}