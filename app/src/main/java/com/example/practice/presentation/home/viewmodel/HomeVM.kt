package com.example.practice.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.data.firebase.firestore.Product
import com.example.practice.data.network.ConnectionState
import com.example.practice.data.network.Response
import com.example.practice.data.persistence.OperationState
import com.example.practice.domain.repository.home.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeVM(private val repo: IHomeRepository): ViewModel() {

    private val _homeProduct = MutableLiveData<Product>()
    private val _homeListCart = MutableLiveData<MutableList<Product>>()
    private val _homeListProducts = MutableLiveData<MutableList<Product>>()
    private val _homeTotal = MutableLiveData<Double>()

    val homeProduct: LiveData<Product>
        get() = _homeProduct
    val homeListCart: LiveData<MutableList<Product>>
        get() = _homeListCart
    val homeListProducts: LiveData<MutableList<Product>>
        get() = _homeListProducts
    val homeTotal: LiveData<Double>
        get() = _homeTotal

    init {
        _homeListCart.value = mutableListOf()
        _homeListProducts.value = mutableListOf()
        _homeTotal.value = 0.0
    }

    fun setProduct(value: Product) {
        _homeProduct.value = value
    }

    fun addProductToCart(product: Product): String {
        return when (val result = repo.addProductToCart(product, _homeListCart)) {
            is OperationState.Success -> {
                result.state
            }
            is OperationState.Error -> {
                result.state
            }
        }
    }

    fun addCount(product: Product): Boolean {
        val list = _homeListCart.value?.filter { it.name == product.name }
        return if (!list.isNullOrEmpty()) {
            val index = homeListCart.value?.indexOf(list[0])
            _homeListCart.value?.set((index?:0), list[0].also { it.count = (it.count?:0)+1 })
            _homeTotal.value = calculateTotal()
            true
        } else {
            false
        }
    }

    fun minusCount(product: Product): Boolean {
        val list = _homeListCart.value?.filter { it.name == product.name }
        return if (!list.isNullOrEmpty()) {
            val index = homeListCart.value?.indexOf(list[0])
            _homeListCart.value?.set((index?:0), list[0].also { it.count = (it.count?:0)-1 })
            _homeTotal.value = calculateTotal()
            true
        } else {
            false
        }
    }

    fun deleteProduct(product: Product): Boolean {
        return if (homeListCart.value?.contains(product) == true) {
            _homeListCart.value?.remove(product)
            _homeTotal.value = calculateTotal()
            true
        } else {
            false
        }
    }

    private fun calculateTotal(): Double {
        var total = 0.0
        homeListCart.value?.forEach { value ->
            total += (value.count?:0)*(value.price?:0.0)
        }
        return total
    }

    suspend fun getAllProductsFromRemote(): ConnectionState<Response> {
        return withContext(Dispatchers.IO) {
            try {
                val result = repo.getAllProductsFromRemote().await()
                val documents = result.documents
                val list = mutableListOf<Product>()
                if (documents.isNotEmpty()) {
                    documents.forEach { value ->
                        list.add(value.toObject(Product::class.java)!!)
                    }
                    repo.saveProducts(list.toList(), _homeListProducts)
                }
                ConnectionState.Success(
                    Response(
                        list = list,
                        message = "La consulta se realizó exitosamente."
                    )
                )
            } catch (e: Exception) {
                ConnectionState.Error(Response(message = "La consulta falló."), e)
            }
        }
    }
}