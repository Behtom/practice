package com.example.practice.data.adapters

import android.content.Context
import com.example.practice.R
import com.example.practice.core.base.BaseAdapter
import com.example.practice.data.persistence.firestore.Product
import com.example.practice.databinding.ItemProductBinding

class ProductAdapter(context: Context, private val listener: IAdapterListener<Product>): BaseAdapter<ItemProductBinding, Product>(context) {
    override fun getLayoutResId(): Int = R.layout.item_product

    override fun onBindData(model: Product, position: Int, binding: ItemProductBinding) {
        binding.model = model
        binding.listener = listener
        binding.position = position
    }
}