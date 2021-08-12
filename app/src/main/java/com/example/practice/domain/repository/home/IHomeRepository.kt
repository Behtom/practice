package com.example.practice.domain.repository.home

import androidx.lifecycle.MutableLiveData
import com.example.practice.data.firebase.firestore.Product
import com.example.practice.data.persistence.OperationState
import com.example.practice.data.persistence.Result
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface IHomeRepository {
    fun addProductToCart(product: Product, persistence: MutableLiveData<MutableList<Product>>): OperationState<String>
    fun saveProducts(products: List<Product>, persistence: MutableLiveData<MutableList<Product>>): OperationState<Result<Product>>
    suspend fun getAllProductsFromRemote(): Task<QuerySnapshot>
    /*suspend fun insertProduct(product: Products)
    suspend fun insertProducts(products: List<Products>)*/
}