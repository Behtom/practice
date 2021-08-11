package com.example.practice.data.persistence.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.practice.data.persistence.db.entities.ShoppingCart
import com.example.practice.data.persistence.db.entities.ShoppingCartWithProducts

@Dao
interface ShoppingCartDao {
    @Transaction
    @Query("SELECT * FROM shopping_cart")
    suspend fun getShoppingCartWithProducts(): LiveData<List<ShoppingCartWithProducts>>

    @Insert
    suspend fun insert(shoppingCartDao: ShoppingCart)
}