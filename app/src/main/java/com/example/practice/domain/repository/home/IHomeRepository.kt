package com.example.practice.domain.repository.home

import com.example.practice.data.persistence.db.entities.Products
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface IHomeRepository {
    suspend fun getAllProductsFromRemote(): Task<QuerySnapshot>
    /*suspend fun insertProduct(product: Products)
    suspend fun insertProducts(products: List<Products>)*/
}