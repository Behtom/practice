package com.example.practice.core.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
abstract class BaseAdapter<B, D>(open var context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract fun getLayoutResId(): Int
    abstract fun onBindData(model: D, position: Int, binding: B)

    var list = emptyList<D>()

    fun setData(items: List<D>) {
        list = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindData(list[position], position, (holder as BaseAdapter<*, *>.ItemViewHolder).mDataBinding as B)
    }

    inner class ItemViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
        val mDataBinding: B = binding as B
    }
}