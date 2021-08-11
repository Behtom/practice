package com.example.practice.data.persistence.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "shopping_cart")
data class ShoppingCart(
    @PrimaryKey(autoGenerate = true) val scId: Long,
    @ColumnInfo(name="product_name") val productName: String?,
    @ColumnInfo(name="total_products") val totalProducts: Int?
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}