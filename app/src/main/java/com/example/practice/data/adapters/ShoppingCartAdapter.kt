package com.example.practice.data.adapters

import android.content.Context
import com.example.practice.R
import com.example.practice.core.base.BaseAdapter
import com.example.practice.data.firebase.firestore.Product
import com.example.practice.databinding.ItemShoppingCartBinding

class ShoppingCartAdapter(context: Context, val listener: IAdapterListener<Product>): BaseAdapter<ItemShoppingCartBinding, Product>(context) {
    override fun getLayoutResId(): Int = R.layout.item_shopping_cart

    override fun onBindData(model: Product, position: Int, binding: ItemShoppingCartBinding) {
        binding.context = context
        binding.model = model
        binding.listener = listener
    }
}