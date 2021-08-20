package com.example.practice.data.adapters

import android.content.Context
import com.example.practice.R
import com.example.practice.core.base.BaseAdapter
import com.example.practice.data.firebase.firestore.Product
import com.example.practice.databinding.ItemTicketBinding

class TicketAdapter(context: Context): BaseAdapter<ItemTicketBinding, Product>(context) {
    override fun getLayoutResId(): Int = R.layout.item_ticket

    override fun onBindData(model: Product, position: Int, binding: ItemTicketBinding) {
        binding.model = model
    }
}