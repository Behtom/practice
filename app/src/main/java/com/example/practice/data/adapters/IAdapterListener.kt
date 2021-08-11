package com.example.practice.data.adapters

interface IAdapterListener<T> {
    fun onProductAdapterListener(model: T, position: Int) {}
}