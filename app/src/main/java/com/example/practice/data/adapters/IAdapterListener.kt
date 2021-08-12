package com.example.practice.data.adapters

interface IAdapterListener<T> {
    fun onProductAdapterListener(model: T, position: Int) {}
    fun addProductToCart(model: T) {}
    fun addCount(model: T) {}
    fun minusCount(model: T) {}
    fun deleteProduct(model: T) {}
}