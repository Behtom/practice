package com.example.practice.data.persistence.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "products")
data class Products(
    @PrimaryKey val name: String,
    @ColumnInfo val price: Double,
    @ColumnInfo val count: Int,
    @ColumnInfo(name = "large_description") val largeDescription: String?,
    @ColumnInfo(name = "short_description") val shortDescription: String?,
    val shoppingCartId: Long?
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}