package com.example.practice.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.data.firebase.firestore.Product
import com.example.practice.data.network.ConnectionState
import com.example.practice.data.network.Response
import com.example.practice.domain.repository.home.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeVM(private val repo: IHomeRepository): ViewModel() {

    private val _homeProduct = MutableLiveData<Product>()

    val homeProduct : LiveData<Product>
        get() = _homeProduct

    fun setProduct(value: Product) {
        _homeProduct.value = value
    }

    suspend fun getAllProductsFromRemote(): ConnectionState<Response> {
        return withContext(Dispatchers.IO) {
            try {
                val result = repo.getAllProductsFromRemote().await()
                ConnectionState.Success(
                    Response(
                        querySnapshot = result,
                        message = "La consulta se realizó exitosamente."
                    )
                )
            } catch (e: Exception) {
                ConnectionState.Error(Response(message = "La consulta falló."), e)
            }
        }
    }
}