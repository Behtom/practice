package com.example.practice.domain.repository.home

import androidx.lifecycle.MutableLiveData
import com.example.practice.data.firebase.firestore.FirestoreManager
import com.example.practice.data.firebase.firestore.Product
import com.example.practice.data.persistence.OperationState
import com.example.practice.data.persistence.Result
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val firestoreManager: FirestoreManager
): IHomeRepository {

    override fun addProductToCart(
        product: Product,
        persistence: MutableLiveData<MutableList<Product>>
    ): OperationState<String> {
        val list = persistence.value
        return when {
            list?.none { it.name == product.name } == true -> {
                list.add(product.also { it.count = 1 })
                OperationState.Success("El producto se ha agregado a tu carrito.")
            }
            list == null -> {
                OperationState.Error("Ha ocurrido un error al agregar el producto a tu carrito.", Exception("The product is already exist"))
            }
            else -> {
                OperationState.Error("El producto ya existe actualmente en tu carrito", Exception("The product is already exist"))
            }
        }
    }

    override fun saveProducts(products: List<Product>, persistence: MutableLiveData<MutableList<Product>>): OperationState<Result<Product>> {
        return if (persistence.value?.addAll(products) == true) {
            OperationState.Success(Result(list = persistence.value?.toList()?:listOf()))
        } else {
            OperationState.Error(Result(message = "Los datos no pudieron ser obtenidos."), Exception("Some exception."))
        }
    }

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